"use client"

import "./Main.css"
import moment from "moment"
import "moment/locale/pt-br"
import { useState, useEffect } from "react"
import { shiftHours } from "../../constants/shift-hours"
import Swal from "sweetalert2"
import logoImg from "../../assets/logo-javacortando-cortada-removebg-preview.png"

moment.locale("pt-br")

function Main() {
  const [availableDates, setAvailableDates] = useState([])
  const [selectedDate, setSelectedDate] = useState(moment())
  const [selectedShift, setSelectedShift] = useState("morning")
  const [selectedHour, setSelectedHour] = useState(null)
  const [availableHours, setAvailableHours] = useState(shiftHours)

  useEffect(() => {
    const dates = []
    for (let i = 0; i < 7; i++) {
      dates.push(moment().add(i, "days"))
    }
    setAvailableDates(dates)
  }, [])

  const handleDateClick = (date) => {
    setSelectedDate(date)
    setSelectedHour(null)
    setSelectedShift("morning")
  }

  const handleShiftClick = (shift) => {
    setSelectedShift(shift)
  }

  const handleHourClick = (hour) => {
    setSelectedHour(hour)
  }

  const handleConfirmClick = () => {
    if (selectedHour.confirmed) {
      return
    }

    setSelectedHour({
      ...selectedHour,
      confirmed: true,
    })

    setAvailableHours((prevHours) => ({
      ...prevHours,
      [selectedShift]: prevHours[selectedShift].map((hour) =>
        hour.start === selectedHour.start ? { ...hour, confirmed: true } : hour,
      ),
    }))

    Swal.fire({
      title: "Agendamento confirmado!",
      text: `Seu horário foi agendado para ${selectedDate.format("DD/MM/YYYY")} às ${selectedHour.start}`,
      icon: "success",
      confirmButtonColor: "#B8860B",
      confirmButtonText: "OK",
    })
  }

  return (
    <div className="main-page">
      {}
      <div className="background-overlay"></div>
      <div className="gradient-overlay"></div>

      <div className="main-content">
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
        <div className="main-card">
          <div className="main__content">
            <h1 className="main__title">Olá, Davi Mendes!</h1>
            <p className="main__subtitle">A Barbearia GiuCortesFreeStyle 💈, lhe dá as boas vindas!</p>
          </div>

          <h2 className="main__cta">Agende seu corte</h2>
          <div className="main__available-dates">
            {availableDates.map((date, index) => (
              <div
                key={index}
                className={`main__date-card ${selectedDate && selectedDate.isSame(date, "day") ? "selected" : ""}`}
                onClick={() => handleDateClick(date)}
              >
                <p className="main__date-card__day">{date.format("DD")}</p>
                <span className="main__date-card__weekday">{date.format("ddd")}</span>
              </div>
            ))}
          </div>

          <h2 className="main__cta">Escolha o melhor horário</h2>
          <div className="main__available-shifts">
            <button
              onClick={() => handleShiftClick("morning")}
              className={`shift-button ${selectedShift === "morning" ? "selected" : ""}`}
            >
              Manhã
            </button>
            <button
              onClick={() => handleShiftClick("afternoon")}
              className={`shift-button ${selectedShift === "afternoon" ? "selected" : ""}`}
            >
              Tarde
            </button>
            <button
              onClick={() => handleShiftClick("night")}
              className={`shift-button ${selectedShift === "night" ? "selected" : ""}`}
            >
              Noite
            </button>
          </div>

          <div className="main__available-hours">
            {availableHours[selectedShift].map((hour, index) => (
              <button
                key={index}
                onClick={() => handleHourClick(hour)}
                className={`hour-button ${selectedHour?.start === hour.start ? "selected" : ""} ${
                  hour.confirmed ? "confirmed" : ""
                }`}
              >
                {hour.start}
              </button>
            ))}
          </div>

          <button
            className="main__confirm-button"
            disabled={!selectedDate || !selectedShift || !selectedHour || selectedHour.confirmed}
            onClick={handleConfirmClick}
          >
            Confirmar agendamento
          </button>
        </div>

        {}
        <div className="footer">© {new Date().getFullYear()} JavaCortando • Todos os direitos reservados</div>
      </div>
    </div>
  )
}

export default Main
