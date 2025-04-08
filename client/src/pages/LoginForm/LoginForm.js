import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './LoginForm.css';

function LoginForm() {
	const location = useLocation();
	const navigate = useNavigate();
	const tipoUsuario = location.state?.tipo || 'cliente';
	const [email, setEmail] = useState('');
	const [senha, setSenha] = useState('');

	const handleSubmit = (e) => {
		e.preventDefault();
		navigate('/');
	};

	return (
		<div className="login-form-container">
			<h1>Login {tipoUsuario === 'cliente' ? 'Cliente' : 'Barbeiro'}</h1>
			<form onSubmit={handleSubmit}>
				<div className="input-group">
					<label htmlFor="email">E-mail</label>
					<input
						type="email"
						id="email"
						value={email}
						onChange={(e) => setEmail(e.target.value)}
						required
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
					/>
				</div>
				<button type="submit" className="botao-login">
					Entrar
				</button>
			</form>
			<p className="link-cadastro">
				Não tem uma conta?{' '}
				<button
					className="link-botao"
					onClick={() => navigate('/cadastro')}
				>
					Cadastre-se
				</button>
			</p>
		</div>
	);
}

export default LoginForm;
