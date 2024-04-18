import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { PeliculasComponent } from './pages/peliculas/peliculas.component';
import { DirectoresComponent } from './pages/directores/directores.component';
import { ActoresComponent } from './pages/actores/actores.component';
import { GenerosComponent } from './pages/generos/generos.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { DetallePeliculaComponent } from './pages/detalle-pelicula/detalle-pelicula.component';
import { PeliculasPorGeneroComponent } from './pages/peliculas-por-genero/peliculas-por-genero.component';

const routes: Routes = [
  {path:'',redirectTo:'/inicio', pathMatch:'full'},
  {path: 'inicio', component: DashboardComponent},
  {path:'iniciar-sesion',component: LoginComponent},
  {path: 'registro', component: RegisterComponent},
  {path: 'peliculas', component: PeliculasComponent},
  {path: 'directores', component: DirectoresComponent},
  {path: 'actores', component: ActoresComponent},
  {path: 'generos', component: GenerosComponent},
  { path: 'peliculas/:id', component: DetallePeliculaComponent },
  { path: 'peliculas/genero/:id', component: PeliculasPorGeneroComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
