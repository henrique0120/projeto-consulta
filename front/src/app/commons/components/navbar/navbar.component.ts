import { Component, Input} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: false,
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent{

  @Input()
  homeButton = 'Controle de pacientes'
  @Input()
  home = ''

  constructor(private readonly router: Router){}



  navigateTo() {
    if (this.router.url !== '/home') {
      this.router.navigate(['/home']);
    }else{
      this.router.navigate(['patients/new-patient'])
    }
  }

  logout() {
  // lógica de logout
  console.log('Usuário deslogado');
  // Exemplo: redirecionar para a tela de login
  this.router.navigate(['/login']);
}

}
