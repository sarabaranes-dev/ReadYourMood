import { AppBar, Toolbar, Typography, Button, Box } from '@mui/material';

export default function Navbar() {
  const urlParams = new URLSearchParams(window.location.search);
  const ruolo = urlParams.get('ruolo');
  const nome = urlParams.get('nome'); 

  const isLogged = nome && nome !== 'null' && nome !== 'anonymousUser' && nome.trim() !== '';

  return (
    <AppBar position="static" color="inherit" elevation={1} sx={{ backgroundColor: '#fff', borderBottom: '2px solid #eaeaea' }}>
      <Toolbar sx={{ display: 'flex', justifyContent: 'space-between', flexWrap: 'wrap' }}>
        
        <Typography 
          variant="h6" 
          component="a" 
          href="http://localhost:8081/"
          sx={{ fontWeight: 'bold', textDecoration: 'none', color: '#845763', cursor: 'pointer', fontFamily: 'Georgia, serif' }}
        >
          Read<span style={{color: '#E491A6'}}>Your</span>Mood 📚
        </Typography>

        <Box sx={{ display: 'flex', gap: 1 }}>
          <Button href="http://localhost:8081/libri" sx={{ fontWeight: 'bold', color: 'primary.main' }}>
            📖 Libri
          </Button>
          <Button href="http://localhost:8081/libri/cerca-emozione" sx={{ color: 'text.secondary' }}>
            🎭 Per emozione
          </Button>
        </Box>

        <Box sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
          {!isLogged ? (
            <>
              <Button href="http://localhost:8081/login" sx={{ color: 'primary.main', fontWeight: 'bold' }}>Accedi</Button>
              <Button href="http://localhost:8081/register" sx={{ color: 'text.secondary' }}>Registrati</Button>
            </>
          ) : (
            <>
              {}
              <Button 
                href="http://localhost:8081/area-personale" 
                sx={{ color: 'primary.main', fontWeight: 'bold', textTransform: 'capitalize' }}
              >
                👤 {nome}
              </Button>
              
              {ruolo === 'ADMIN' && (
                <Button href="http://localhost:8081/admin" color="inherit" sx={{ fontWeight: 'bold' }}>⚙️ Admin</Button>
              )}
              
              <Button href="http://localhost:8081/logout" variant="outlined" color="error" size="small" sx={{ borderRadius: '20px' }}>
                Logout
              </Button>
            </>
          )}
        </Box>
      </Toolbar>
    </AppBar>
  );
}