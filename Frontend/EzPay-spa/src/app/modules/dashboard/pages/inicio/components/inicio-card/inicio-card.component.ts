import { Component } from '@angular/core';

@Component({
  selector: 'app-inicio-card',
  templateUrl: './inicio-card.component.html',
  styleUrls: ['./inicio-card.component.css'],
})
export class InicioCardComponent {
  showBalance = false;

  public onShowBalance= ()=>{
    this.showBalance = !this.showBalance;
  }
}
