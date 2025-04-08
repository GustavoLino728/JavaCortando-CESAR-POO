import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Cadastro.css';

function Cadastro() {
	const navigate = useNavigate();
	const [nome, setNome] = useState('');
	const [email, setEmail] = useState('');
	const [senha, setSenha] = useState('');
	const [confirmarSenha, setConfirmarSenha] = useState('');

	const handleSubmit = (e) => {
		e.preventDefault();
		if (senha !== confirmarSenha) {
			alert('As senhas não coincidem!');
			return;
		}
		// Aqui você implementará a lógica de cadastro
		console.log({ nome, email, senha });
	};

	return (
		<div className="cadastro-container">
			<h1>Cadastro</h1>
			<form onSubmit={handleSubmit}>
				<div className="input-group">
					<label htmlFor="nome">Nome</label>
					<input
						type="text"
						id="nome"
						value={nome}
						onChange={(e) => setNome(e.target.value)}
						required
					/>
				</div>
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
				<div className="input-group">
					<label htmlFor="confirmarSenha">Confirmar Senha</label>
					<input
						type="password"
						id="confirmarSenha"
						value={confirmarSenha}
						onChange={(e) => setConfirmarSenha(e.target.value)}
						required
					/>
				</div>
				<button type="submit" className="botao-login">
					Cadastrar
				</button>
			</form>
			<p className="link-login">
				Já tem uma conta?{' '}
				<button 
					className="link-botao"
					onClick={() => navigate('/login')}
				>
					Faça login
				</button>
			</p>
		</div>
	);
}

export default Cadastro; 