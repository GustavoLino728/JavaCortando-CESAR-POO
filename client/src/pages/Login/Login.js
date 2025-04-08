import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';

function Login() {
	const navigate = useNavigate();
	const [tipoLogin, setTipoLogin] = useState('');

	const handleTipoLogin = (tipo) => {
		setTipoLogin(tipo);
		navigate('/login-form', { state: { tipo } });
	};

	return (
		<div className="login-container">
			<h1>Fazer login</h1>
			<div className="botoes-container">
				<button 
					className="botao-login"
					onClick={() => handleTipoLogin('cliente')}
				>
					Como cliente
				</button>
				<button 
					className="botao-login"
					onClick={() => handleTipoLogin('barbeiro')}
				>
					Como barbeiro
				</button>
			</div>
		</div>
	);
}

export default Login; 