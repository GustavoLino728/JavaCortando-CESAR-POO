import React, { useState, useEffect } from 'react';
import { useAuth } from '../../contexts/AuthContext';
import { 
	listarHorariosDisponiveis, 
	agendarCorte, 
	cancelarCorte, 
	listarMeusCortes 
} from '../../services/api';

const ClienteDashboard = () => {
	const { user, signOut } = useAuth();
	const [horariosDisponiveis, setHorariosDisponiveis] = useState([]);
	const [meusCortes, setMeusCortes] = useState([]);
	const [dataSelecionada, setDataSelecionada] = useState('');
	const [horarioSelecionado, setHorarioSelecionado] = useState(null);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState('');

	useEffect(() => {
		carregarDados();
	}, []);

	const carregarDados = async () => {
		try {
			setLoading(true);
			const [horarios, cortes] = await Promise.all([
				listarHorariosDisponiveis(),
				listarMeusCortes()
			]);
			setHorariosDisponiveis(horarios);
			setMeusCortes(cortes);
		} catch (error) {
			setError('Erro ao carregar dados');
			console.error(error);
		} finally {
			setLoading(false);
		}
	};

	const handleAgendar = async () => {
		try {
			if (!dataSelecionada || !horarioSelecionado) {
				setError('Selecione uma data e horário');
				return;
			}

			await agendarCorte(dataSelecionada, horarioSelecionado);
			await carregarDados();
			setError('');
		} catch (error) {
			setError('Erro ao agendar corte');
			console.error(error);
		}
	};

	const handleCancelar = async (data, horario) => {
		try {
			await cancelarCorte(data, horario);
			await carregarDados();
			setError('');
		} catch (error) {
			setError('Erro ao cancelar corte');
			console.error(error);
		}
	};

	if (loading) {
		return <div className="flex justify-center items-center h-screen">Carregando...</div>;
	}

	return (
		<div className="max-w-[600px] mx-auto p-4">
			<div className="flex justify-between items-center mb-6">
				<h1 className="text-2xl font-bold">Olá, {user?.username}</h1>
				<button
					onClick={signOut}
					className="bg-[#B8860B] text-white px-4 py-2 rounded"
				>
					Sair
				</button>
			</div>

			{error && (
				<div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
					{error}
				</div>
			)}

			<div className="mb-8">
				<h2 className="text-xl font-semibold mb-4">Agendar Novo Corte</h2>
				<div className="space-y-4">
					<input
						type="date"
						value={dataSelecionada}
						onChange={(e) => setDataSelecionada(e.target.value)}
						className="w-full p-2 border rounded"
					/>
					<select
						value={horarioSelecionado || ''}
						onChange={(e) => setHorarioSelecionado(parseFloat(e.target.value))}
						className="w-full p-2 border rounded"
					>
						<option value="">Selecione um horário</option>
						{horariosDisponiveis.map((horario) => (
							<option key={horario} value={horario}>
								{horario}:00
							</option>
						))}
					</select>
					<button
						onClick={handleAgendar}
						className="w-full bg-[#B8860B] text-white py-2 rounded"
					>
						Agendar
					</button>
				</div>
			</div>

			<div>
				<h2 className="text-xl font-semibold mb-4">Meus Cortes</h2>
				{meusCortes.length === 0 ? (
					<p>Você não tem cortes agendados</p>
				) : (
					<div className="space-y-4">
						{meusCortes.map((corte) => (
							<div
								key={`${corte.data}-${corte.horario}`}
								className="border p-4 rounded flex justify-between items-center"
							>
								<div>
									<p>Data: {new Date(corte.data).toLocaleDateString()}</p>
									<p>Horário: {corte.horario}:00</p>
								</div>
								<button
									onClick={() => handleCancelar(corte.data, corte.horario)}
									className="bg-red-500 text-white px-4 py-2 rounded"
								>
									Cancelar
								</button>
							</div>
						))}
					</div>
				)}
			</div>
		</div>
	);
};

export default ClienteDashboard; 