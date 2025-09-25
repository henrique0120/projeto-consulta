import { Component } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-menu-bar',
  standalone: false,
  templateUrl: './menu-bar.component.html',
  styleUrl: './menu-bar.component.css'
})
export class MenuBarComponent {

  constructor(private readonly router: Router){}

  navigateTo(path: string) {
    this.router.navigate([path])
  }

}
