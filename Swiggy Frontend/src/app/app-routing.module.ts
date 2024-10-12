import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './about/about.component';
import { AppComponent } from './app.component';
import { AuthGuard } from './auth.guard';
import { CartComponent } from './cart/cart.component';
import { ContactComponent } from './contact/contact.component';
import { FoodsComponent } from './foods/foods.component';
import { HomeComponent } from './home/home.component';
import { LogoutComponent } from './logout/logout.component';
import { MainscreenComponent } from './mainscreen/mainscreen.component';
import { ProfileComponent } from './profile/profile.component';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { SignupComponent } from './signup/signup.component';

const routes: Routes = [
  {path:'', component: MainscreenComponent},
  {path:'login', component: HomeComponent},
  {path:'about', component: AboutComponent},
  {path:'cart', component: CartComponent,canActivate: [AuthGuard]},
  {path:'contact', component: ContactComponent},
  {path:'food', component: FoodsComponent, canActivate: [AuthGuard]},
  {path:'restaurant', component: RestaurantsComponent},
  {path:'profile', component: ProfileComponent},
  {path:'signup', component: SignupComponent},
  {path:'logout', component: LogoutComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
