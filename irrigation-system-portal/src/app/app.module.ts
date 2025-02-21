import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { CoreModule } from './core/core.module';
import { LayoutModule } from './views/layout/layout.module';
import { NgModule } from '@angular/core';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SettingsComponent } from './components/settings/settings.component';
import { SavePlotModalComponent } from './components/save-plot-modal/save-plot-modal.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [AppComponent, DashboardComponent, SettingsComponent, SavePlotModalComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    // Core module of your application
    CoreModule,
    // Layout module of your application
    LayoutModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
