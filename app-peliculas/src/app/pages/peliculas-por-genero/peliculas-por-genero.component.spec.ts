import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeliculasPorGeneroComponent } from './peliculas-por-genero.component';

describe('PeliculasPorGeneroComponent', () => {
  let component: PeliculasPorGeneroComponent;
  let fixture: ComponentFixture<PeliculasPorGeneroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PeliculasPorGeneroComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PeliculasPorGeneroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
