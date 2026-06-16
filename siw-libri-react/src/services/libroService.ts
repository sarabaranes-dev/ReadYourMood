import { api } from './api';
import type { Libro } from '../types';

export async function getLibri(): Promise<Libro[]> {
  const { data } = await api.get<Libro[]>('/rest/libri');
  return data;
}