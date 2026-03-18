import { useState } from 'react'
import { useNavigate, Link, useLocation } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'

export default function Login() {
  const { login } = useAuth()
  const navigate = useNavigate()
  const location = useLocation()
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  // Mensaje de éxito si viene del registro
  const justRegistered = location.state?.registered

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      await login(username, password)
      navigate('/dashboard')
    } catch {
      setError('Usuario o contraseña incorrectos')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h1 style={styles.title}>Banking App</h1>
        <p style={styles.subtitle}>Ingresá a tu cuenta</p>
        {justRegistered && (
          <p style={styles.success}>¡Cuenta creada! Ya podés iniciar sesión.</p>
        )}
        <form onSubmit={handleSubmit} style={styles.form}>
          <input
            style={styles.input}
            type="text"
            placeholder="Usuario"
            value={username}
            onChange={e => setUsername(e.target.value)}
            required
          />
          <input
            style={styles.input}
            type="password"
            placeholder="Contraseña"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
          />
          {error && <p style={styles.error}>{error}</p>}
          <button style={styles.button} type="submit" disabled={loading}>
            {loading ? 'Ingresando...' : 'Ingresar'}
          </button>
        </form>
        <p style={styles.linkText}>
          ¿No tenés cuenta?{' '}
          <Link to="/register" style={styles.link}>Registrate acá</Link>
        </p>
      </div>
    </div>
  )
}

const styles = {
  container: { minHeight:'100vh', display:'flex', alignItems:'center', justifyContent:'center', background:'#f0f4f8' },
  card: { background:'#fff', borderRadius:16, padding:'2.5rem', width:360, boxShadow:'0 4px 24px rgba(0,0,0,0.08)' },
  title: { margin:'0 0 4px', fontSize:24, fontWeight:600, color:'#1a2b4b' },
  subtitle: { margin:'0 0 1.5rem', color:'#666', fontSize:14 },
  form: { display:'flex', flexDirection:'column', gap:12 },
  input: { padding:'12px 14px', borderRadius:8, border:'1.5px solid #ddd', fontSize:15, outline:'none' },
  button: { padding:'13px', borderRadius:8, background:'#1a2b4b', color:'#fff', border:'none', fontSize:15, fontWeight:600, cursor:'pointer', marginTop:4 },
  error: { color:'#e53e3e', fontSize:13, margin:0 },
  success: { color:'#276749', background:'#f0fff4', border:'1px solid #9ae6b4', borderRadius:8, padding:'10px 14px', fontSize:13, marginBottom:8 },
  linkText: { textAlign:'center', marginTop:'1.2rem', fontSize:14, color:'#666' },
  link: { color:'#1a2b4b', fontWeight:600, textDecoration:'none' },
}