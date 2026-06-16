import { useState, useEffect } from 'react';
import LibriFilterGrid from '../components/LibriFilterGrid';
import type { Libro } from '../types'; // Assicurati che il percorso sia giusto

export default function LibreriaPage() {
  const [libri, setLibri] = useState<Libro[]>([]);

  useEffect(() => {
    fetch('http://localhost:8081/rest/libri')
      .then((res) => res.json())
      .then((data) => setLibri(data))
      .catch((err) => console.error("Errore nel caricamento dei libri:", err));
  }, []);


  return (
    <LibriFilterGrid libri={libri} />
  );
}