import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderComponent } from './shared/header/header.component';
import { NavComponent } from './shared/nav/nav.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { PeliculasComponent } from './pages/peliculas/peliculas.component';
import { DirectoresComponent } from './pages/directores/directores.component';
import { ActoresComponent } from './pages/actores/actores.component';
import { GenerosComponent } from './pages/generos/generos.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { DetallePeliculaComponent } from './pages/detalle-pelicula/detalle-pelicula.component';
import { PeliculasPorGeneroComponent } from './pages/peliculas-por-genero/peliculas-por-genero.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    NavComponent,
    RegisterComponent, 
    LoginComponent,
    DashboardComponent,
    PeliculasComponent,
    DirectoresComponent,
    ActoresComponent,
    GenerosComponent,
    DetallePeliculaComponent,
    PeliculasPorGeneroComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
