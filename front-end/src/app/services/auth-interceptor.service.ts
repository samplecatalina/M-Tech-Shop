import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { OKTA_AUTH } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';
import { Observable, from, lastValueFrom } from 'rxjs';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  constructor(@Inject(OKTA_AUTH) private oktaAuth: OktaAuth) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return from(this.handleAccess(req, next));
  }

  private async handleAccess(req: HttpRequest<any>, next: HttpHandler): Promise<HttpEvent<any>> {

    const theEndpoint = environment['mtechshopApiUrl'] + '/orders';

    // only add an access token for secured endpoints
    const securedEndpoints = [theEndpoint];

    if (securedEndpoints.some(url => req.urlWithParams.includes(url))) {
      // get access token
      const accessToken = await this.oktaAuth.getAccessToken();

      // clone the request and add new header with access token
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + accessToken
        }
      });
    }

    return await lastValueFrom(next.handle(req));
  }
}
