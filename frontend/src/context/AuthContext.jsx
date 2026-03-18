import { createContext, useContext, useState } from 'react'
import api from '../api/axios'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const token = sessionStorage.getItem('token')
    const username = sessionStorage.getItem('username')
    return token ? { token, username } : null
  })

  const login = async (username, password) => {
    const { data } = await api.post('/auth/login', { username, password })
    sessionStorage.setItem('token', data.token)
    sessionStorage.setItem('username', data.username)
    setUser({ token: data.token, username: data.username })
  }

  const logout = () => {
    sessionStorage.clear()
    setUser(null)
  }

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext)