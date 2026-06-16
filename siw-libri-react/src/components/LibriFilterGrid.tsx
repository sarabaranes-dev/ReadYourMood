import { useState } from 'react';
import type { Libro } from '../types';
import { TextField, Card, CardContent, Typography, Button, Box, MenuItem, Select, InputLabel, FormControl, type SelectChangeEvent, ThemeProvider, createTheme } from '@mui/material';
import Navbar from './Navbar';

//creo tema per avere stessi colori di thymeleaf
const rymTheme = createTheme({
  palette: {
    primary: { main: '#845763' }, 
    secondary: { main: '#E491A6' }, 
    background: { default: '#FDF8F9' }
  },
  shape: { borderRadius: 16 }, 
  typography: { fontFamily: 'Georgia, serif' }
});

interface Props {
  libri: Libro[];
}

export default function LibriFilterGrid({ libri }: Props) {
  const [titolo, setTitolo] = useState('');
  const [autore, setAutore] = useState('');
  const [anno, setAnno] = useState<number | ''>('');
  const [valutazione, setValutazione] = useState<number | ''>('');

  const libriFiltrati = Array.isArray(libri) ? libri.filter((libro) => {
    const matchTitolo = (libro?.titolo || "").toLowerCase().includes(titolo.toLowerCase());
    const matchAutore = (libro?.autore || "").toLowerCase().includes(autore.toLowerCase());
    const matchAnno = anno === '' || libro.anno === anno;
    const matchValutazione = valutazione === '' || libro.valutazione === valutazione;
    return matchTitolo && matchAutore && matchAnno && matchValutazione;
  }) : [];

  const handleValutazioneChange = (event: SelectChangeEvent<number | ''>) => {
    setValutazione(event.target.value as number | '');
  };

  return (
    <ThemeProvider theme={rymTheme}>
      <Box sx={{ minHeight: '100vh', bgcolor: 'background.default' }}>
        
        {}
        <Navbar /> 

        <Box sx={{ maxWidth: 1200, margin: '0 auto', p: 3, pt: 5 }}>
          <Typography variant="h4" sx={{ mb: 4, fontWeight: 'bold', color: 'primary.main' }}>
            📖 Catalogo Libri
          </Typography>

          {/* ZONA FILTRI */}
          <Box sx={{ display: 'flex', gap: 2, flexWrap: 'wrap', mb: 5, backgroundColor: 'white', p: 3, borderRadius: '16px', border: '1px solid #f0e8eb', boxShadow: '0 4px 20px rgba(0,0,0,0.04)' }}>
            <TextField
              label="Cerca per Titolo" variant="outlined" size="small"
              value={titolo} onChange={(e) => setTitolo(e.target.value)} sx={{ flexGrow: 1, minWidth: '200px', fontFamily: 'Segoe UI' }}
            />
            <TextField
              label="Cerca per Autore" variant="outlined" size="small"
              value={autore} onChange={(e) => setAutore(e.target.value)} sx={{ flexGrow: 1, minWidth: '200px' }}
            />
            <TextField
              label="Anno" variant="outlined" type="number" size="small"
              value={anno} onChange={(e) => setAnno(e.target.value ? Number(e.target.value) : '')} sx={{ width: '120px' }}
            />
            <FormControl size="small" sx={{ width: '150px' }}>
              <InputLabel>Valutazione</InputLabel>
              <Select value={valutazione} label="Valutazione" onChange={handleValutazioneChange}>
                <MenuItem value=""><em>Tutte</em></MenuItem>
                <MenuItem value={5}>⭐⭐⭐⭐⭐</MenuItem>
                <MenuItem value={4}>⭐⭐⭐⭐</MenuItem>
                <MenuItem value={3}>⭐⭐⭐</MenuItem>
                <MenuItem value={2}>⭐⭐</MenuItem>
                <MenuItem value={1}>⭐</MenuItem>
              </Select>
            </FormControl>
          </Box>

          {/* ZONA GRIGLIA RISULTATI */}
          <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(220px, 1fr))', gap: 4 }}>
            {libriFiltrati.map((libro) => (
              <Card key={libro.id} elevation={0} sx={{ display: 'flex', flexDirection: 'column', height: '100%', border: '1px solid #f0e8eb', transition: 'transform 0.2s, box-shadow 0.2s', '&:hover': { transform: 'translateY(-4px)', boxShadow: '0 8px 25px rgba(228, 145, 166, 0.3)' } }}>
                
                {/* Immagine da Spring Boot */}
                <Box sx={{ width: '100%', height: 280, backgroundColor: '#FAE4EC', display: 'flex', alignItems: 'center', justifyContent: 'center', overflow: 'hidden' }}>
                  {libro.foto ? (
                    <img src={`http://localhost:8081/uploads/${libro.foto}`} alt="Copertina" style={{ width: '100%', height: '100%', objectFit: 'cover' }} />
                  ) : (
                    <Typography variant="h2">📖</Typography>
                  )}
                </Box>

                <CardContent sx={{ flexGrow: 1, p: 2 }}>
                  <Typography variant="subtitle1" sx={{ fontWeight: 'bold', lineHeight: 1.2, mb: 0.5, fontFamily: 'Segoe UI' }}>
                    {libro?.titolo}
                  </Typography>
                  <Typography variant="body2" sx={{ color: 'text.secondary', mb: 1, fontFamily: 'Segoe UI' }}>
                    di {libro?.autore} ({libro?.anno})
                  </Typography>
                  <Typography variant="body2" sx={{ color: 'secondary.main', mb: 1, letterSpacing: 2 }}>
                    {'★'.repeat(libro?.valutazione || 0)}{'☆'.repeat(5 - (libro?.valutazione || 0))}
                  </Typography>
                  <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mt: 2 }}>
                    <Typography variant="h6" sx={{ color: 'primary.main', fontWeight: 'bold' }}>
                      € {(libro.prezzo).toFixed(2).replace('.', ',')}
                    </Typography>
                  </Box>
                </CardContent>
                
                <Box sx={{ p: 2, pt: 0 }}>
                  <Button variant="contained" color="secondary" fullWidth sx={{ borderRadius: '30px', fontWeight: 'bold', textTransform: 'none', fontFamily: 'Segoe UI' }} href={`http://localhost:8081/libri/${libro.id}`}>
                    Vedi Dettagli
                  </Button>
                </Box>
              </Card>
            ))}
            
            {libriFiltrati.length === 0 && (
              <Box sx={{ gridColumn: '1 / -1', textAlign: 'center', py: 8 }}>
                <Typography variant="h1" sx={{ mb: 2 }}>📭</Typography>
                <Typography variant="h6" sx={{ color: 'text.secondary' }}>
                  Nessun libro trovato con questi filtri.
                </Typography>
              </Box>
            )}
          </Box>

        </Box>
      </Box>
    </ThemeProvider>
  );
}