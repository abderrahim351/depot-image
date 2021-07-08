import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import {ToggleButtonModule} from 'primeng/togglebutton';
import {CarouselModule} from 'primeng/carousel';
import { acceuilservice } from '../app/accueil/acceuilservice';
import {CheckboxModule} from 'primeng/checkbox';

import { AppComponent } from './app.component';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { CalendarModule } from 'primeng/calendar';
import { SliderModule } from 'primeng/slider';
import { MultiSelectModule } from 'primeng/multiselect';
import { ContextMenuModule } from 'primeng/contextmenu';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ProgressBarModule } from 'primeng/progressbar';
import { InputTextModule } from 'primeng/inputtext';
import { FileUploadModule } from 'primeng/fileupload';
import { ToolbarModule } from 'primeng/toolbar';
import { RatingModule } from 'primeng/rating';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputNumberModule } from 'primeng/inputnumber';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { InputTextareaModule } from 'primeng/inputtextarea';
import {PanelModule} from 'primeng/panel';
import {MenubarModule} from 'primeng/menubar';
import {TabViewModule} from 'primeng/tabview';
import {GalleriaModule} from 'primeng/galleria';
import {SplitButtonModule} from 'primeng/splitbutton';
import {BlockUIModule} from 'primeng/blockui';
import {TreeTableModule} from 'primeng/treetable';

import { ListeUtilisateurComponent } from './admin/liste-utilisateur/liste-utilisateur.component';
import { HeaderComponent } from './vues/header/header.component';
import { FooterComponent } from './vues/footer/footer.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { CardModule } from 'primeng/card';
import { PasswordModule } from 'primeng/password';
import { AccueilComponent } from './accueil/accueil.component';
import { NouveauImageComponent } from './nouveau-image/nouveau-image.component';
import {DataViewModule} from 'primeng/dataview';
import { ImageDetailsComponent } from './image-details/image-details.component';
import { InformationProfilComponent } from './information-profil/information-profil.component';
import {ProgressSpinnerModule} from 'primeng/progressspinner';
import { CommentaireComponent } from './commentaire/commentaire.component';
import { CommentBlockComponent } from './commentaire/comment-block/comment-block.component';
import { ImageMetasComponent } from './image-metas/image-metas.component';
import { InscriptionComponent } from './inscription/inscription.component';


@NgModule({
  declarations: [
    AppComponent,
    ListeUtilisateurComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
      AccueilComponent,
      NouveauImageComponent,
      ImageDetailsComponent,
      InformationProfilComponent,
      CommentaireComponent,
      CommentBlockComponent,
      ImageMetasComponent,
      InscriptionComponent


   ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    TableModule,
    CalendarModule,
    SliderModule,
    DialogModule,
    MultiSelectModule,
    ContextMenuModule,
    DropdownModule,
    ButtonModule,
    ToastModule,
    InputTextModule,
    ProgressBarModule,
    HttpClientModule,
    FileUploadModule,
    ToolbarModule,
    RatingModule,
    FormsModule,
    ReactiveFormsModule,
    RadioButtonModule,
    InputNumberModule,
    ConfirmDialogModule,
    InputTextareaModule,
    CardModule,
    PasswordModule,
    CheckboxModule,
    ToggleButtonModule,
    PanelModule,
    CarouselModule,
    DataViewModule,
    MenubarModule,
    ProgressSpinnerModule,
    GalleriaModule,
    SplitButtonModule,
    BlockUIModule,
    TabViewModule,
    TreeTableModule,
  ],
  providers: [ MessageService, ConfirmationService,acceuilservice],
  bootstrap: [AppComponent],

})
export class AppModule {}
