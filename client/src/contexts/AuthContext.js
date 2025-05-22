import React, { createContext, useState, useContext, useEffect } from 'react';
import { loginCliente, loginBarbeiro } from '../services/api';

const AuthContext = createContext({});

export const AuthProvider = ({ children }) => {
	const [user, setUser] = useState(null);
	const [loading, setLoading] = useState(true);

	useEffect(() => {
		const storedUser = localStorage.getItem('user');
		if (storedUser) {
			setUser(JSON.parse(storedUser));
		}
		setLoading(false);
	}, []);

	const signInCliente = async (username, password) => {
		try {
			const response = await loginCliente(username, password);
			const userData = {
				username: response.login,
				token: response.token,
				role: 'cliente'
			};
			setUser(userData);
			localStorage.setItem('user', JSON.stringify(userData));
			localStorage.setItem('token', response.token);
			return true;
		} catch (error) {
			console.error('Erro no login:', error);
			return false;
		}
	};

	const signInBarbeiro = async (username, password) => {
		try {
			const response = await loginBarbeiro(username, password);
			const userData = {
				username: response.login,
				token: response.token,
				role: 'barbeiro'
			};
			setUser(userData);
			localStorage.setItem('user', JSON.stringify(userData));
			localStorage.setItem('token', response.token);
			return true;
		} catch (error) {
			console.error('Erro no login:', error);
			return false;
		}
	};

	const signOut = () => {
		setUser(null);
		localStorage.removeItem('user');
		localStorage.removeItem('token');
	};

	return (
		<AuthContext.Provider value={{ 
			signed: !!user, 
			user, 
			loading, 
			signInCliente, 
			signInBarbeiro, 
			signOut 
		}}>
			{children}
		</AuthContext.Provider>
	);
};

export const useAuth = () => {
	const context = useContext(AuthContext);
	return context;
};
