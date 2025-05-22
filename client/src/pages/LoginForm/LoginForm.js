import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useAuth } from '../../contexts/AuthContext';
import './LoginForm.css';

function LoginForm() {
	const location = useLocation();
	const navigate = useNavigate();
	const { signInCliente, signInBarbeiro } = useAuth();
	const tipoUsuario = location.state?.tipo || 'cliente';
	const [username, setUsername] = useState('');
	const [senha, setSenha] = useState('');
	const [error, setError] = useState('');
	const [loading, setLoading] = useState(false);

	const handleSubmit = async (e) => {
		e.preventDefault();
		setError('');
		setLoading(true);

		try {
			const success = tipoUsuario === 'cliente'
				? await signInCliente(username, senha)
				: await signInBarbeiro(username, senha);

			if (success) {
				navigate(tipoUsuario === 'cliente' ? '/' : '/barbeiro');
			} else {
				setError('Usuário ou senha inválidos');
			}
		} catch (error) {
			setError('Erro ao fazer login. Tente novamente.');
			console.error(error);
		} finally {
			setLoading(false);
		}
	};

	return (
		<div className="login-form-container">
			<h1>Login {tipoUsuario === 'cliente' ? 'Cliente' : 'Barbeiro'}</h1>
			
			{error && (
				<div className="error-message">
					{error}
				</div>
			)}

			<form onSubmit={handleSubmit}>
				<div className="input-group">
					<label htmlFor="username">Usuário</label>
					<input
						type="text"
						id="username"
						value={username}
						onChange={(e) => setUsername(e.target.value)}
						required
						disabled={loading}
					/>
				</div>
				<div className="input-group">
					<label htmlFor="senha">Senha</label>
					<input
						type="password"
						id="senha"
						value={senha}
						onChange={(e) => setSenha(e.target.value)}
						required
						disabled={loading}
					/>
				</div>
				<button 
					type="submit" 
					className="botao-login"
					disabled={loading}
				>
					{loading ? 'Entrando...' : 'Entrar'}
				</button>
			</form>
			<p className="link-cadastro">
				Não tem uma conta?{' '}
				<button
					className="link-botao"
					onClick={() => navigate('/cadastro')}
					disabled={loading}
				>
					Cadastre-se
				</button>
			</p>
		</div>
	);
}

export default LoginForm;
