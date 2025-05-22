import React, { useState, useEffect } from 'react';
import { useAuth } from '../../contexts/AuthContext';
import { listarCortesBarbeiro } from '../../services/api';

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

			<div>
				<h2 className="text-xl font-semibold mb-4">Cortes Agendados</h2>
				{cortes.length === 0 ? (
					<p>Não há cortes agendados</p>
				) : (
					<div className="space-y-4">
						{cortes.map((corte) => (
							<div
								key={`${corte.data}-${corte.horario}`}
								className="border p-4 rounded"
							>
								<div className="flex justify-between items-start">
									<div>
										<p className="font-semibold">Cliente: {corte.cliente?.username}</p>
										<p>Data: {new Date(corte.data).toLocaleDateString()}</p>
										<p>Horário: {corte.horario}:00</p>
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