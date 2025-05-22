import axios from 'axios';

const api = axios.create({
	baseURL: 'http://localhost:8080',
});

api.interceptors.request.use((config) => {
	const token = localStorage.getItem('token');
	if (token) {
		config.headers.Authorization = token;
	}
	return config;
});

export const loginCliente = async (username, password) => {
	const response = await api.post('/login-cliente', { username, password });
	return response.data;
};

export const loginBarbeiro = async (username, password) => {
	const response = await api.post('/login-barbeiro', { username, password });
	return response.data;
};

export const cadastrarCliente = async (clienteData) => {
	const response = await api.post('/cliente/cadastro', clienteData);
	return response.data;
};

export const listarHorariosDisponiveis = async () => {
	const response = await api.get('/home');
	return response.data;
};

export const agendarCorte = async (data, horario) => {
	const response = await api.post('/cliente/marcar', { data, horario });
	return response.data;
};

export const cancelarCorte = async (id) => {
	const response = await api.delete(`/cliente/desmarcar/${id}`);
	return response.data;
};

export const listarMeusCortes = async () => {
	const response = await api.get('/cliente/meus-cortes');
	return response.data;
};

export const listarCortesBarbeiro = async () => {
	const response = await api.get('/barbeiro/ver-cortes');
	return response.data;
};

export default api;
