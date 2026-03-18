import { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom'
import api from '../api/axios'

export default function Register() {
  const navigate = useNavigate()
  const [form, setForm] = useState({ username: '', email: '', password: '', confirm: '' })
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value })
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')

    if (form.password !== form.confirm) {
      setError('Las contraseñas no coinciden')
      return
    }

    setLoading(true)
    try {
      await api.post('/auth/register', {
        username: form.username,
        email: form.email,
        password: form.password,
      })
      navigate('/login', { state: { registered: true } })
    } catch (err) {
      setError(err.response?.data?.error || 'Error al registrarse')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div style={styles.container}>
      <div style={styles.card}>
        <h1 style={styles.title}>Crear cuenta</h1>
        <p style={styles.subtitle}>Completá tus datos para registrarte</p>
        <form onSubmit={handleSubmit} style={styles.form}>
          <input
            style={styles.input}
            type="text"
            name="username"
            placeholder="Usuario"
            value={form.username}
            onChange={handleChange}
            required
          />
          <input
            style={styles.input}
            type="email"
            name="email"
            placeholder="Email"
            value={form.email}
            onChange={handleChange}
            required
          />
          <input
            style={styles.input}
            type="password"
            name="password"
            placeholder="Contraseña (mínimo 6 caracteres)"
            value={form.password}
            onChange={handleChange}
            required
          />
          <input
            style={styles.input}
            type="password"
            name="confirm"
            placeholder="Confirmar contraseña"
            value={form.confirm}
            onChange={handleChange}
            required
          />
          {error && <p style={styles.error}>{error}</p>}
          <button style={styles.button} type="submit" disabled={loading}>
            {loading ? 'Registrando...' : 'Crear cuenta'}
          </button>
        </form>
        <p style={styles.linkText}>
          ¿Ya tenés cuenta?{' '}
          <Link to="/login" style={styles.link}>Ingresá acá</Link>
        </p>
      </div>
    </div>
  )
}

const styles = {
  container: { minHeight:'100vh', display:'flex', alignItems:'center', justifyContent:'center', background:'#f0f4f8' },
  card: { background:'#fff', borderRadius:16, padding:'2.5rem', width:380, boxShadow:'0 4px 24px rgba(0,0,0,0.08)' },
  title: { margin:'0 0 4px', fontSize:24, fontWeight:600, color:'#1a2b4b' },
  subtitle: { margin:'0 0 1.5rem', color:'#666', fontSize:14 },
  form: { display:'flex', flexDirection:'column', gap:12 },
  input: { padding:'12px 14px', borderRadius:8, border:'1.5px solid #ddd', fontSize:15, outline:'none' },
  button: { padding:'13px', borderRadius:8, background:'#1a2b4b', color:'#fff', border:'none', fontSize:15, fontWeight:600, cursor:'pointer', marginTop:4 },
  error: { color:'#e53e3e', fontSize:13, margin:0 },
  linkText: { textAlign:'center', marginTop:'1.2rem', fontSize:14, color:'#666' },
  link: { color:'#1a2b4b', fontWeight:600, textDecoration:'none' },
}