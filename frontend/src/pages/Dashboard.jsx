import { useAuth } from '../context/AuthContext'
import { useNavigate } from 'react-router-dom'

export default function Dashboard() {
  const { user, logout } = useAuth()
  const navigate = useNavigate()

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <div style={{ padding: '2rem', fontFamily: 'sans-serif' }}>
      <h2>Bienvenido, {user?.username} 👋</h2>
      <p>Panel principal — acá van las funciones bancarias.</p>
      <button onClick={handleLogout}
        style={{ padding:'10px 20px', background:'#e53e3e', color:'#fff', border:'none', borderRadius:8, cursor:'pointer' }}>
        Cerrar sesión
      </button>
    </div>
  )
}