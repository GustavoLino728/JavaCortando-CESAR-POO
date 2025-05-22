import React, { useState, useEffect } from 'react';
import { useAuth } from '../../contexts/AuthContext';
import { listarCortesBarbeiro } from '../../services/api';
import moment from 'moment';
import 'moment/locale/pt-br';

moment.locale('pt-br');

const BarbeiroDashboard = () => {
	const { user, signOut } = useAuth();
	const [cortes, setCortes] = useState([]);
	const [loading, setLoading] = useState(true);
	const [error, setError] = useState('');

	useEffect(() => {
		carregarCortes();
	}, []);

	const carregarCortes = async () => {
		try {
			setLoading(true);
			const response = await listarCortesBarbeiro();
			setCortes(response);
		} catch (error) {
			setError('Erro ao carregar cortes');
			console.error(error);
		} finally {
			setLoading(false);
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

			<div className="bg-white rounded-lg shadow-md p-6">
				<h2 className="text-xl font-semibold text-gray-800 mb-6">Cortes Agendados</h2>
				{cortes.length === 0 ? (
					<div className="text-center py-8">
						<p className="text-gray-500">Não há cortes agendados</p>
					</div>
				) : (
					<div className="grid gap-4">
						{cortes.map((corte) => (
							<div
								key={`${corte.data}-${corte.horario}`}
								className="bg-gray-50 rounded-lg p-4 border border-gray-200 hover:border-[#B8860B] transition-colors duration-200"
							>
								<div className="flex justify-between items-start">
									<div className="space-y-2">
										<div className="flex items-center space-x-2">
											<span className="text-[#B8860B] font-semibold">Cliente:</span>
											<span className="text-gray-700">{corte.cliente?.username}</span>
										</div>
										<div className="flex items-center space-x-2">
											<span className="text-[#B8860B] font-semibold">Data:</span>
											<span className="text-gray-700">{moment(corte.data).format('DD/MM/YYYY')}</span>
										</div>
										<div className="flex items-center space-x-2">
											<span className="text-[#B8860B] font-semibold">Horário:</span>
											<span className="text-gray-700">{corte.horario}:00</span>
										</div>
									</div>
									<div className="bg-[#B8860B] text-white px-3 py-1 rounded-full text-sm">
										Agendado
									</div>
								</div>
							</div>
						))}
					</div>
				)}
			</div>
		</div>
	);
};

export default BarbeiroDashboard;
