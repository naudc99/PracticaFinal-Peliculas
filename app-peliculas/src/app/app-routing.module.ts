import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { PeliculasComponent } from './pages/peliculas/peliculas.component';
import { DirectoresComponent } from './pages/directores/directores.component';
import { ActoresComponent } from './pages/actores/actores.component';
import { GenerosComponent } from './pages/generos/generos.component';

const routes: Routes = [
  {path:'',redirectTo:'/inicio', pathMatch:'full'},
  {path:'iniciar-sesion',component: LoginComponent},
  {path: 'registro', component: RegisterComponent},
  {path: 'peliculas', component: PeliculasComponent},
  {path: 'directores', component: DirectoresComponent},
  {path: 'actores', component: ActoresComponent},
  {path: 'generos', component: GenerosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
