"use client"

import { useState } from "react"
import { useNavigate } from "react-router-dom"
import "./Login.css"
import logoImg from "../../assets/logo-javacortando-cortada-removebg-preview.png"

function Login() {
  const navigate = useNavigate()
  const [tipoLogin, setTipoLogin] = useState("")
  const [hoverButton, setHoverButton] = useState("")

  const handleTipoLogin = (tipo) => {
    setTipoLogin(tipo)
    navigate("/login-form", { state: { tipo } })
  }

  return (
    <div className="login-container">
      {}
      <div className="background-overlay"></div>
      <div className="gradient-overlay"></div>

      {}
      <div className="login-content">
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
        <div className="login-card">
          {}
          <div className="background-image-container"></div>

          {}
          <div className="login-form">
            <h1>Fazer Login</h1>
            <div className="botoes-container">
              <button
                className={`botao-login ${hoverButton === "cliente" ? "botao-hover" : ""}`}
                onClick={() => handleTipoLogin("cliente")}
                onMouseEnter={() => setHoverButton("cliente")}
                onMouseLeave={() => setHoverButton("")}
              >
                Como Cliente
              </button>
              <button
                className={`botao-login ${hoverButton === "barbeiro" ? "botao-hover" : ""}`}
                onClick={() => handleTipoLogin("barbeiro")}
                onMouseEnter={() => setHoverButton("barbeiro")}
                onMouseLeave={() => setHoverButton("")}
              >
                Como Barbeiro
              </button>
            </div>
          </div>
        </div>

        {}
        <div className="footer">© {new Date().getFullYear()} JavaCortando • Todos os direitos reservados</div>
      </div>
    </div>
  )
}

export default Login
