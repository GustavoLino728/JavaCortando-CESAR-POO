"use client"

import { useState, useEffect, useCallback } from "react"
import { useAuth } from "../../contexts/AuthContext"
import { listarCortesBarbeiro } from "../../services/api"
import moment from "moment"
import "moment/locale/pt-br"
import "./BarbeiroDashboard.css"
import logoImg from "../../assets/logo-javacortando-cortada-removebg-preview.png"

moment.locale("pt-br")

const BarbeiroDashboard = () => {
  const { user, signOut } = useAuth()
  const [cortes, setCortes] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState("")
  const [dadosCarregados, setDadosCarregados] = useState(false)

  const carregarCortes = useCallback(async () => {
    if (dadosCarregados) return

    try {
      setLoading(true)
      const response = await listarCortesBarbeiro()
      setCortes(response)
      setDadosCarregados(true)
    } catch (error) {
      setError("Erro ao carregar cortes")
      console.error(error)
    } finally {
      setLoading(false)
    }
  }, [dadosCarregados])

  useEffect(() => {
    carregarCortes()
  }, [carregarCortes])

  if (loading) {
    return (
      <div className="loading-container">
        <div className="loading-spinner"></div>
      </div>
    )
  }

  return (
    <div className="barbeiro-dashboard-page">
      {}
      <div className="background-overlay"></div>
      <div className="gradient-overlay"></div>

      <div className="barbeiro-dashboard-content">
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
        <div className="barbeiro-dashboard-header">
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
        <div className="barbeiro-dashboard-card">
          <h2>Cortes Agendados</h2>
          {cortes.length === 0 ? (
            <div className="empty-state">
              <p>Não há cortes agendados</p>
            </div>
          ) : (
            <div className="cortes-list">
              {cortes.map((corte) => (
                <div key={`${corte.data}-${corte.horario}`} className="corte-item">
                  <div className="corte-info">
                    <div className="corte-detail">
                      <span className="label">Cliente:</span>
                      <span className="value">{corte.nomeCliente}</span>
                    </div>
                    <div className="corte-detail">
                      <span className="label">Data:</span>
                      <span className="value">{corte.data}</span>
                    </div>
                    <div className="corte-detail">
                      <span className="label">Horário:</span>
                      <span className="value">{corte.horario}</span>
                    </div>
                  </div>
                  <div className="status-badge">Agendado</div>
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

export default BarbeiroDashboard
