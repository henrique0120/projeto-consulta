import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, map, Subscription } from 'rxjs';

@Component({
  selector: 'app-control-page',
  standalone: false,
  templateUrl: './control-page.component.html',
  styleUrl: './control-page.component.css'
})
export class ControlPageComponent implements OnInit, OnDestroy{
  title = 'medical-clinic';

  private routeSubscription?: Subscription;

  constructor(private readonly router: Router, private readonly activatedRoute: ActivatedRoute){

  }

  ngOnDestroy(): void {
    if (this.routeSubscription){
      this.routeSubscription.unsubscribe()
    }
  }

  ngOnInit(): void {
     this.routeSubscription = this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => this.getRouteTitle(this.activatedRoute))
    ).subscribe(title => this.title = title)
  }

  private getRouteTitle(route: ActivatedRoute): string {
    let child = route;
    while (child.firstChild){
      child = child.firstChild;
    }
    return child.snapshot.data['title'] || 'Default Title';
  }


}
