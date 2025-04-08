import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header/Header';
import Login from './pages/Login/Login';
import LoginForm from './pages/LoginForm/LoginForm';
import Cadastro from './pages/Cadastro/Cadastro';
import Main from './pages/Main/Main';
import './App.css';

function App() {
	return (
		<Router>
			<div className="app">
				<Header />
				<Routes>
					<Route path="/" element={<Main />} />
					<Route path="/login" element={<Login />} />
					<Route path="/login-form" element={<LoginForm />} />
					<Route path="/cadastro" element={<Cadastro />} />
				</Routes>
			</div>
		</Router>
	);
}

export default App;
