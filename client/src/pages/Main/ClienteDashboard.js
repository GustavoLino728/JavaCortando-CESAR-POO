"use client"

import { useState, useEffect, useCallback } from "react"
import { useAuth } from "../../contexts/AuthContext"
import { listarHorariosDisponiveis, agendarCorte, cancelarCorte, listarMeusCortes } from "../../services/api"
import moment from "moment"
import "moment/locale/pt-br"
import "./ClienteDashboard.css"
import logoImg from "../../assets/logo-javacortando-cortada-removebg-preview.png"

moment.locale("pt-br")

const ClienteDashboard = () => {
  const { user, signOut } = useAuth()
  const [horariosDisponiveis, setHorariosDisponiveis] = useState([])
  const [meusCortes, setMeusCortes] = useState([])
  const [dataSelecionada, setDataSelecionada] = useState("")
  const [horarioSelecionado, setHorarioSelecionado] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState("")
  const [dadosCarregados, setDadosCarregados] = useState(false)

  const formatarHorario = (horarioFloat) => {
    const horas = Math.floor(horarioFloat)
    const minutos = Math.round((horarioFloat - horas) * 60)
    return `${horas.toString().padStart(2, "0")}:${minutos.toString().padStart(2, "0")}`
  }

  const carregarDados = useCallback(async () => {
    if (dadosCarregados) return

    try {
      setLoading(true)
      const [horarios, cortes] = await Promise.all([listarHorariosDisponiveis(), listarMeusCortes()])
      setHorariosDisponiveis(horarios)
      setMeusCortes(cortes)
      setDadosCarregados(true)
    } catch (error) {
      setError("Erro ao carregar dados")
      console.error(error)
    } finally {
      setLoading(false)
    }
  }, [dadosCarregados])

  useEffect(() => {
    carregarDados()
  }, [carregarDados])

  const handleAgendar = async () => {
    try {
      if (!dataSelecionada || !horarioSelecionado) {
        setError("Selecione uma data e horário")
        return
      }

      await agendarCorte(dataSelecionada, horarioSelecionado)
      setDadosCarregados(false)
      await carregarDados()
      setError("")
    } catch (error) {
      setError("Erro ao agendar corte")
      console.error(error)
    }
  }

  const handleCancelar = async (id) => {
    try {
      await cancelarCorte(id)
      setDadosCarregados(false)
      await carregarDados()
      setError("")
    } catch (error) {
      setError("Erro ao cancelar corte")
      console.error(error)
    }
  }

  if (loading) {
    return (
      <div className="loading-container">
        <div className="loading-spinner"></div>
      </div>
    )
  }

  return (
    <div className="dashboard-page">
      {}
      <div className="background-overlay"></div>
      <div className="gradient-overlay"></div>

      <div className="dashboard-content">
        {}
        <div className="logo-container">
          <img
            src={logoImg || "/placeholder.svg"}
            alt="JavaCortando Logo"
            className="logo"
            onError={(e) => {
              e.target.onerror = null
              e.target.src = "/placeholder.svg"
            }}
          />
        </div>

        {}
        <div className="dashboard-header">
          <div className="user-info">
            <h1>Olá, {user?.username}</h1>
            <p>Bem-vindo ao seu painel</p>
          </div>
          <button onClick={signOut} className="logout-button">
            Sair
          </button>
        </div>

        {}
        {error && <div className="error-message">{error}</div>}

        {}
        <div className="dashboard-card">
          <h2>Agendar Novo Corte</h2>
          <div className="form-group">
            <label>Data</label>
            <input
              type="date"
              value={dataSelecionada}
              onChange={(e) => setDataSelecionada(e.target.value)}
              className="form-input"
            />
          </div>
          <div className="form-group">
            <label>Horário</label>
            <select
              value={horarioSelecionado || ""}
              onChange={(e) => setHorarioSelecionado(Number.parseFloat(e.target.value))}
              className="form-select"
            >
              <option value="">Selecione um horário</option>
              {horariosDisponiveis.map((horario) => (
                <option key={horario} value={horario}>
                  {formatarHorario(horario)}
                </option>
              ))}
            </select>
          </div>
          <button onClick={handleAgendar} className="agendar-button">
            Agendar Corte
          </button>
        </div>

        {}
        <div className="dashboard-card">
          <h2>Meus Cortes</h2>
          {meusCortes.length === 0 ? (
            <div className="empty-state">
              <p>Você não tem cortes agendados</p>
            </div>
          ) : (
            <div className="cortes-list">
              {meusCortes.map((corte) => (
                <div key={`${corte.data}-${corte.horario}`} className="corte-item">
                  <div className="corte-info">
                    <div className="corte-detail">
                      <span className="label">Data:</span>
                      <span className="value">{corte.data}</span>
                    </div>
                    <div className="corte-detail">
                      <span className="label">Horário:</span>
                      <span className="value">{corte.horario}</span>
                    </div>
                  </div>
                  <button onClick={() => handleCancelar(corte.id)} className="cancelar-button">
                    Cancelar
                  </button>
                </div>
              ))}
            </div>
          )}
        </div>

        {}
        <div className="footer">© {new Date().getFullYear()} JavaCortando • Todos os direitos reservados</div>
      </div>
    </div>
  )
}

export default ClienteDashboard
