import './Main.css';
import moment from 'moment';
import 'moment/locale/pt-br';
import { useState, useEffect } from 'react';
import { shiftHours } from '../../constants/shift-hours';
import Swal from 'sweetalert2';

moment.locale('pt-br');

function Main() {
	const [availableDates, setAvailableDates] = useState([]);
	const [selectedDate, setSelectedDate] = useState(moment());
	const [selectedShift, setSelectedShift] = useState('morning');
	const [selectedHour, setSelectedHour] = useState(null);
	const [availableHours, setAvailableHours] = useState(shiftHours);

	useEffect(() => {
		const dates = [];
		for (let i = 0; i < 7; i++) {
			dates.push(moment().add(i, 'days'));
		}
		setAvailableDates(dates);
	}, []);

	const handleDateClick = (date) => {
		setSelectedDate(date);
		setSelectedHour(null);
		setSelectedShift('morning');
	}

	const handleShiftClick = (shift) => {
		setSelectedShift(shift);
	}

	const handleHourClick = (hour) => {
		setSelectedHour(hour);
	}

	const handleConfirmClick = () => {
		if (selectedHour.confirmed) {
			return;
		}

		setSelectedHour({
			...selectedHour,
			confirmed: true
		});

		setAvailableHours(prevHours => ({
			...prevHours,
			[selectedShift]: prevHours[selectedShift].map(hour =>
				hour.start === selectedHour.start ? { ...hour, confirmed: true } : hour
			)
		}));

		Swal.fire({
			title: 'Agendamento confirmado!',
			text: `Seu horário foi agendado para ${selectedDate.format('DD/MM/YYYY')} às ${selectedHour.start}`,
			icon: 'success',
			confirmButtonColor: '#B8860B',
			confirmButtonText: 'OK'
		});
	}

	return (
		<div className="main">
			<div className='main__content'>
				<h1 className='main__title'>Olá, Davi Mendes!</h1>
				<p className='main__subtitle'>A Barbearia GiuCortesFreeStyle 💈, lhe dá as boas vindas!</p>
			</div>
			<h2 className='main__cta'>Agende seu corte</h2>
			<div className='main__available-dates'>
				{availableDates.map((date, index) => (
					<div key={index} className={`main__date-card ${selectedDate && selectedDate.isSame(date, 'day') ? 'selected' : ''}`} onClick={() => handleDateClick(date)}>
						<p className='main__date-card__day'>{date.format('DD')}</p>
						<span className='main__date-card__weekday'>{date.format('ddd')}</span>
					</div>
				))}
			</div>
			<h2 className='main__cta'>Escolha o melhor horário</h2>
			<div className='main__available-shifts'>
				<button onClick={() => handleShiftClick('morning')} className={`${selectedShift === 'morning' ? 'selected' : ''}`}>
					Manhã
				</button>
				<button onClick={() => handleShiftClick('afternoon')} className={`${selectedShift === 'afternoon' ? 'selected' : ''}`}>
					Tarde
				</button>
				<button onClick={() => handleShiftClick('night')} className={`${selectedShift === 'night' ? 'selected' : ''}`}>
					Noite
				</button>
			</div>
			<div className='main__available-hours'>
				{availableHours[selectedShift].map((hour, index) => (
					<button key={index} onClick={() => handleHourClick(hour)} className={`${selectedHour?.start === hour.start ? 'selected' : ''} ${hour.confirmed ? 'confirmed' : ''}`}>
						{hour.start}
					</button>
				))}
			</div>
			<button className='main__confirm-button' disabled={!selectedDate || !selectedShift || !selectedHour || selectedHour.confirmed}
				onClick={handleConfirmClick}>
				Confirmar agendamento
			</button>
		</div>
	);
}

export default Main;
