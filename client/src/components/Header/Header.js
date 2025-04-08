import Logo from '../../assets/logo.svg';
import Background from '../../assets/background.png';
import './Header.css';
import { Link } from 'react-router-dom';

function Header() {
	return (
		<header>
			<Link to="/">
				<img src={Logo} alt="Logo" />
			</Link>
			<img className='background' src={Background} alt="Background" />
		</header>
	)
}

export default Header;
