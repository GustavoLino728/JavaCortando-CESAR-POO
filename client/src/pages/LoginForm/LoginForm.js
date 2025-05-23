"use client"

import { useState } from "react"
import { useLocation, useNavigate } from "react-router-dom"
import { useAuth } from "../../contexts/AuthContext"
import "./LoginForm.css"
import logoImg from "../../assets/logo-javacortando-cortada-removebg-preview.png"

function LoginForm() {
  const location = useLocation()
  const navigate = useNavigate()
  const { signInCliente, signInBarbeiro } = useAuth()
  const tipoUsuario = location.state?.tipo || "cliente"
  const [username, setUsername] = useState("")
  const [senha, setSenha] = useState("")
  const [error, setError] = useState("")
  const [loading, setLoading] = useState(false)
  const [focusedField, setFocusedField] = useState("")

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError("")
    setLoading(true)

    try {
      const success =
        tipoUsuario === "cliente" ? await signInCliente(username, senha) : await signInBarbeiro(username, senha)

      if (success) {
        navigate(tipoUsuario === "cliente" ? "/" : "/barbeiro")
      } else {
        setError("Usuário ou senha inválidos")
      }
    } catch (error) {
      setError("Erro ao fazer login. Tente novamente.")
      console.error(error)
    } finally {
      setLoading(false)
    }
  }

  const handleVoltar = () => {
    navigate("/")
  }

  return (
    <div className="login-form-page">
      {}
      <div className="background-overlay"></div>
      <div className="gradient-overlay"></div>

      <div className="login-form-content">
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
        <div className="login-form-card">
          <button className="voltar-button" onClick={handleVoltar}>
            &larr; Voltar
          </button>

          <h1>Login {tipoUsuario === "cliente" ? "Cliente" : "Barbeiro"}</h1>

          {error && <div className="error-message">{error}</div>}

          <form onSubmit={handleSubmit}>
            <div className={`input-group ${focusedField === "username" ? "focused" : ""}`}>
              <label htmlFor="username">Usuário</label>
              <input
                type="text"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
                disabled={loading}
                onFocus={() => setFocusedField("username")}
                onBlur={() => setFocusedField("")}
              />
            </div>
            <div className={`input-group ${focusedField === "senha" ? "focused" : ""}`}>
              <label htmlFor="senha">Senha</label>
              <input
                type="password"
                id="senha"
                value={senha}
                onChange={(e) => setSenha(e.target.value)}
                required
                disabled={loading}
                onFocus={() => setFocusedField("senha")}
                onBlur={() => setFocusedField("")}
              />
            </div>
            <button type="submit" className="botao-login" disabled={loading}>
              {loading ? "Entrando..." : "Entrar"}
            </button>
          </form>

          <p className="link-cadastro">
            {tipoUsuario === "cliente" && (
              <>
                Não tem uma conta?{" "}
                <button className="link-botao" onClick={() => navigate("/cadastro")} disabled={loading}>
                  Cadastre-se
                </button>
              </>
            )}
          </p>
        </div>

        {}
        <div className="footer">© {new Date().getFullYear()} JavaCortando • Todos os direitos reservados</div>
      </div>
    </div>
  )
}

export default LoginForm
