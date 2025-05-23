"use client"

import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { cadastrarCliente } from "../../services/api"
import "./Cadastro.css"
import logoImg from "../../assets/logo-javacortando-cortada-removebg-preview.png"

function Cadastro() {
  const navigate = useNavigate()
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    telefone: "",
  })
  const [confirmarSenha, setConfirmarSenha] = useState("")
  const [error, setError] = useState("")
  const [loading, setLoading] = useState(false)
  const [focusedField, setFocusedField] = useState("")

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }))
  }

  const validateForm = () => {
    if (formData.password !== confirmarSenha) {
      setError("As senhas não coincidem!")
      return false
    }

    if (formData.password.length < 6) {
      setError("A senha deve ter pelo menos 6 caracteres")
      return false
    }

    if (!formData.telefone.match(/^\d{10,11}$/)) {
      setError("Telefone inválido. Use apenas números (10 ou 11 dígitos)")
      return false
    }

    if (!formData.email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
      setError("Email inválido")
      return false
    }

    return true
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError("")

    if (!validateForm()) {
      return
    }

    setLoading(true)

    try {
      await cadastrarCliente(formData)
      navigate("/login", { state: { tipo: "cliente" } })
    } catch (error) {
      console.error("Erro no cadastro:", error)
      setError("Erro ao cadastrar. Tente novamente.")
    } finally {
      setLoading(false)
    }
  }

  const handleVoltar = () => {
    navigate("/login")
  }

  return (
    <div className="cadastro-page">
      {}
      <div className="background-overlay"></div>
      <div className="gradient-overlay"></div>

      <div className="cadastro-content">
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
        <div className="cadastro-card">
          <button className="voltar-button" onClick={handleVoltar}>
            &larr; Voltar
          </button>

          <h1>Cadastro</h1>

          {error && <div className="error-message">{error}</div>}

          <form onSubmit={handleSubmit}>
            <div className={`input-group ${focusedField === "username" ? "focused" : ""}`}>
              <label htmlFor="username">Usuário</label>
              <input
                type="text"
                id="username"
                name="username"
                value={formData.username}
                onChange={handleChange}
                required
                disabled={loading}
                placeholder="Digite seu nome"
                onFocus={() => setFocusedField("username")}
                onBlur={() => setFocusedField("")}
              />
            </div>

            <div className={`input-group ${focusedField === "email" ? "focused" : ""}`}>
              <label htmlFor="email">E-mail</label>
              <input
                type="email"
                id="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                disabled={loading}
                placeholder="Digite seu email"
                onFocus={() => setFocusedField("email")}
                onBlur={() => setFocusedField("")}
              />
            </div>

            <div className={`input-group ${focusedField === "telefone" ? "focused" : ""}`}>
              <label htmlFor="telefone">Telefone</label>
              <input
                type="tel"
                id="telefone"
                name="telefone"
                value={formData.telefone}
                onChange={handleChange}
                required
                disabled={loading}
                placeholder="Digite seu telefone (apenas números)"
                onFocus={() => setFocusedField("telefone")}
                onBlur={() => setFocusedField("")}
              />
            </div>

            <div className={`input-group ${focusedField === "password" ? "focused" : ""}`}>
              <label htmlFor="password">Senha</label>
              <input
                type="password"
                id="password"
                name="password"
                value={formData.password}
                onChange={handleChange}
                required
                disabled={loading}
                placeholder="Digite sua senha"
                onFocus={() => setFocusedField("password")}
                onBlur={() => setFocusedField("")}
              />
            </div>

            <div className={`input-group ${focusedField === "confirmarSenha" ? "focused" : ""}`}>
              <label htmlFor="confirmarSenha">Confirmar Senha</label>
              <input
                type="password"
                id="confirmarSenha"
                value={confirmarSenha}
                onChange={(e) => setConfirmarSenha(e.target.value)}
                required
                disabled={loading}
                placeholder="Confirme sua senha"
                onFocus={() => setFocusedField("confirmarSenha")}
                onBlur={() => setFocusedField("")}
              />
            </div>

            <button type="submit" className="botao-cadastro" disabled={loading}>
              {loading ? "Cadastrando..." : "Cadastrar"}
            </button>
          </form>

          <p className="link-login">
            Já tem uma conta?{" "}
            <button className="link-botao" onClick={() => navigate("/login")} disabled={loading}>
              Faça login
            </button>
          </p>
        </div>

        {}
        <div className="footer">© {new Date().getFullYear()} JavaCortando • Todos os direitos reservados</div>
      </div>
    </div>
  )
}

export default Cadastro
