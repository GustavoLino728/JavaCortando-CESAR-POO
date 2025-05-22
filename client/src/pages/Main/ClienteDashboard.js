import React, { useState, useEffect, useCallback } from 'react';
import { useAuth } from '../../contexts/AuthContext';
import { 
	listarHorariosDisponiveis, 
	agendarCorte, 
	cancelarCorte, 
	listarMeusCortes 
} from '../../services/api';
import moment from 'moment';
import 'moment/locale/pt-br';

moment.locale('pt-br');

const ClienteDashboard = () => {
	const { user, signOut } = useAuth();
	const [horariosDisponiveis, setHorariosDisponiveis] = useState([]);
	const [meusCortes, setMeusCortes] = useState([]);
	const [dataSelecionada, setDataSelecionada] = useState('');
	const [horarioSelecionado, setHorarioSelecionado] = useState(null);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState('');
	const [dadosCarregados, setDadosCarregados] = useState(false);

	const formatarHorario = (horarioFloat) => {
		const horas = Math.floor(horarioFloat);
		const minutos = Math.round((horarioFloat - horas) * 60);
		return `${horas.toString().padStart(2, '0')}:${minutos.toString().padStart(2, '0')}`;
	};

	const carregarDados = useCallback(async () => {
		if (dadosCarregados) return;
		
		try {
			setLoading(true);
			const [horarios, cortes] = await Promise.all([
				listarHorariosDisponiveis(),
				listarMeusCortes()
			]);
			setHorariosDisponiveis(horarios);
			setMeusCortes(cortes);
			setDadosCarregados(true);
		} catch (error) {
			setError('Erro ao carregar dados');
			console.error(error);
		} finally {
			setLoading(false);
		}
	}, [dadosCarregados]);

	useEffect(() => {
		carregarDados();
	}, [carregarDados]);

	const handleAgendar = async () => {
		try {
			if (!dataSelecionada || !horarioSelecionado) {
				setError('Selecione uma data e horário');
				return;
			}

			await agendarCorte(dataSelecionada, horarioSelecionado);
			setDadosCarregados(false);
			await carregarDados();
			setError('');
		} catch (error) {
			setError('Erro ao agendar corte');
			console.error(error);
		}
	};

	const handleCancelar = async (id) => {
		try {
			await cancelarCorte(id);
			setDadosCarregados(false);
			await carregarDados();
			setError('');
		} catch (error) {
			setError('Erro ao cancelar corte');
			console.error(error);
		}
	};

	if (loading) {
		return (
			<div className="flex justify-center items-center h-screen">
				<div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-[#B8860B]"></div>
			</div>
		);
	}

	return (
		<div className="max-w-[600px] mx-auto p-4 min-h-screen bg-gray-50">
			<div className="bg-white rounded-lg shadow-md p-6 mb-6">
				<div className="flex justify-between items-center">
					<div>
						<h1 className="text-2xl font-bold text-gray-800">Olá, {user?.username}</h1>
						<p className="text-gray-600">Bem-vindo ao seu painel</p>
					</div>
					<button
						onClick={signOut}
						className="bg-[#B8860B] hover:bg-[#9B7209] text-white px-6 py-2 rounded-lg transition-colors duration-200"
					>
						Sair
					</button>
				</div>
			</div>

			{error && (
				<div className="bg-red-100 border-l-4 border-red-500 text-red-700 p-4 rounded mb-6">
					<p>{error}</p>
				</div>
			)}

			<div className="bg-white rounded-lg shadow-md p-6 mb-6">
				<h2 className="text-xl font-semibold text-gray-800 mb-6">Agendar Novo Corte</h2>
				<div className="space-y-4">
					<div>
						<label className="block text-sm font-medium text-gray-700 mb-1">Data</label>
						<input
							type="date"
							value={dataSelecionada}
							onChange={(e) => setDataSelecionada(e.target.value)}
							className="w-full p-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-[#B8860B] focus:border-[#B8860B] outline-none transition-colors duration-200"
						/>
					</div>
					<div>
						<label className="block text-sm font-medium text-gray-700 mb-1">Horário</label>
						<select
							value={horarioSelecionado || ''}
							onChange={(e) => setHorarioSelecionado(parseFloat(e.target.value))}
							className="w-full p-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-[#B8860B] focus:border-[#B8860B] outline-none transition-colors duration-200"
						>
							<option value="">Selecione um horário</option>
							{horariosDisponiveis.map((horario) => (
								<option key={horario} value={horario}>
									{formatarHorario(horario)}
								</option>
							))}
						</select>
					</div>
					<button
						onClick={handleAgendar}
						className="w-full bg-[#B8860B] hover:bg-[#9B7209] text-white py-3 rounded-lg transition-colors duration-200 font-medium"
					>
						Agendar Corte
					</button>
				</div>
			</div>

			<div className="bg-white rounded-lg shadow-md p-6">
				<h2 className="text-xl font-semibold text-gray-800 mb-6">Meus Cortes</h2>
				{meusCortes.length === 0 ? (
					<div className="text-center py-8">
						<p className="text-gray-500">Você não tem cortes agendados</p>
					</div>
				) : (
					<div className="grid gap-4">
						{meusCortes.map((corte) => (
							<div
								key={`${corte.data}-${corte.horario}`}
								className="bg-gray-50 rounded-lg p-4 border border-gray-200 hover:border-[#B8860B] transition-colors duration-200"
							>
								<div className="flex justify-between items-start">
									<div className="space-y-2">
										<div className="flex items-center space-x-2">
											<span className="text-[#B8860B] font-semibold">Data:</span>
											<span className="text-gray-700">{corte.data}</span>
										</div>
										<div className="flex items-center space-x-2">
											<span className="text-[#B8860B] font-semibold">Horário:</span>
											<span className="text-gray-700">{corte.horario}</span>
										</div>
									</div>
									<button
										onClick={() => handleCancelar(corte.id)}
										className="bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg transition-colors duration-200"
									>
										Cancelar
									</button>
								</div>
							</div>
						))}
					</div>
				)}
			</div>
		</div>
	);
};

export default ClienteDashboard;
