import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { cadastrarCliente } from '../../services/api';
import './Cadastro.css';

function Cadastro() {
	const navigate = useNavigate();
	const [formData, setFormData] = useState({
		username: '',
		email: '',
		password: '',
		telefone: ''
	});
	const [confirmarSenha, setConfirmarSenha] = useState('');
	const [error, setError] = useState('');
	const [loading, setLoading] = useState(false);

	const handleChange = (e) => {
		const { name, value } = e.target;
		setFormData(prev => ({
			...prev,
			[name]: value
		}));
	};

	const validateForm = () => {
		if (formData.password !== confirmarSenha) {
			setError('As senhas não coincidem!');
			return false;
		}

		if (formData.password.length < 6) {
			setError('A senha deve ter pelo menos 6 caracteres');
			return false;
		}

		if (!formData.telefone.match(/^\d{10,11}$/)) {
			setError('Telefone inválido. Use apenas números (10 ou 11 dígitos)');
			return false;
		}

		if (!formData.email.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
			setError('Email inválido');
			return false;
		}

		return true;
	};

	const handleSubmit = async (e) => {
		e.preventDefault();
		setError('');

		if (!validateForm()) {
			return;
		}

		setLoading(true);

		try {
			await cadastrarCliente(formData);
			navigate('/login', { state: { tipo: 'cliente' } });
		} catch (error) {
			console.error('Erro no cadastro:', error);
			setError('Erro ao cadastrar. Tente novamente.');
		} finally {
			setLoading(false);
		}
	};

	return (
		<div className="cadastro-container">
			<h1>Cadastro</h1>

			{error && (
				<div className="error-message">
					{error}
				</div>
			)}

			<form onSubmit={handleSubmit}>
				<div className="input-group">
					<label htmlFor="username">Nome</label>
					<input
						type="text"
						id="username"
						name="username"
						value={formData.username}
						onChange={handleChange}
						required
						disabled={loading}
						placeholder="Digite seu nome"
					/>
				</div>

				<div className="input-group">
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
					/>
				</div>

				<div className="input-group">
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
					/>
				</div>

				<div className="input-group">
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
					/>
				</div>

				<div className="input-group">
					<label htmlFor="confirmarSenha">Confirmar Senha</label>
					<input
						type="password"
						id="confirmarSenha"
						value={confirmarSenha}
						onChange={(e) => setConfirmarSenha(e.target.value)}
						required
						disabled={loading}
						placeholder="Confirme sua senha"
					/>
				</div>

				<button 
					type="submit" 
					className="botao-login"
					disabled={loading}
				>
					{loading ? 'Cadastrando...' : 'Cadastrar'}
				</button>
			</form>

			<p className="link-login">
				Já tem uma conta?{' '}
				<button 
					className="link-botao"
					onClick={() => navigate('/login')}
					disabled={loading}
				>
					Faça login
				</button>
			</p>
		</div>
	);
}

export default Cadastro; 