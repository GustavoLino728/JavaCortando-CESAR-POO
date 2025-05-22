import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './contexts/AuthContext';
import Login from './pages/Login/Login';
import Cadastro from './pages/Cadastro/Cadastro';
import LoginForm from './pages/LoginForm/LoginForm';
import ClienteDashboard from './pages/Main/ClienteDashboard';
import BarbeiroDashboard from './pages/Main/BarbeiroDashboard';
import './App.css';

const PrivateRoute = ({ children, role }) => {
	const { signed, user, loading } = useAuth();

	if (loading) {
		return <div>Carregando...</div>;
	}

	if (!signed) {
		return <Navigate to="/login" />;
	}

	if (role && user.role !== role) {
		return <Navigate to="/" />;
	}

	return children;
};

const App = () => {
	return (
		<AuthProvider>
			<Router>
				<Routes>
					<Route path="/login" element={<Login />} />
					<Route path="/login-form" element={<LoginForm />} />
					<Route path="/cadastro" element={<Cadastro />} />
					<Route
						path="/"
						element={
							<PrivateRoute>
								<ClienteDashboard />
							</PrivateRoute>
						}
					/>
					<Route
						path="/barbeiro"
						element={
							<PrivateRoute role="barbeiro">
								<BarbeiroDashboard />
							</PrivateRoute>
						}
					/>
				</Routes>
			</Router>
		</AuthProvider>
	);
};

export default App;
