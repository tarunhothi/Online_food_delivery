import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FoodsComponent } from './foods/foods.component';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { CartComponent } from './cart/cart.component';
import { ContactComponent } from './contact/contact.component';
import { AboutComponent } from './about/about.component';
import { ProfileComponent } from './profile/profile.component';
import { RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SignupComponent } from './signup/signup.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthGuard } from './auth.guard';
import { AuthService } from './auth.service';
import { TokenInterceptorService } from './token-interceptor.service';
import { HomeService } from './home/home.service';
import { MainscreenComponent } from './mainscreen/mainscreen.component';
import { LogoutComponent } from './logout/logout.component';

@NgModule({
  declarations: [
    AppComponent,
    FoodsComponent,
    RestaurantsComponent,
    CartComponent,
    ContactComponent,
    AboutComponent,
    ProfileComponent,
    HomeComponent,
    SignupComponent,
    MainscreenComponent,
    LogoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true,
    },
    HomeService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
