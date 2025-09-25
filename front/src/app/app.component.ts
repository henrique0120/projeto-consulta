import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, map, Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: false,
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit, OnDestroy{
  textButton: string = ""

  private routeSubscription?: Subscription;

  constructor(public router: Router, private readonly activatedRoute: ActivatedRoute){

  }

  ngOnDestroy(): void {
    if (this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
  }

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateButtonText(event.urlAfterRedirects);
      }
    });
  }

  updateButtonText(url: string) {
    if (url !== '/home') {
      this.textButton = 'Home';
    } else {
      this.textButton = 'Controle de pacientes'; // Botão sem texto na rota `/`
    }
  }


}
