import { Injectable } from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { lastValueFrom } from 'rxjs';
import { CryptoUtils} from '../utils/cryptoUtils'; // Assuming utils are in a utils directory

@Injectable()
export class BankingProfileService {
  constructor(private readonly httpService: HttpService, 
              private readonly cryptoUtils: CryptoUtils) {}

  async getBankingProfile(encryptedUniqueCode: string) {
    const uniqueCode = this.cryptoUtils.decrypt(encryptedUniqueCode);
    

    // Use HttpService for external API calls
    const customerProfile$ = this.httpService.get(`${process.env.CUSTOMERS_API_URL}/customers/${uniqueCode}`);
    const accounts$ = this.httpService.get(`${process.env.CUSTOMER_PRODUCTS_API_URL}/customers/${uniqueCode}/savings_accounts`);
    const cards$ = this.httpService.get(`${process.env.CUSTOMER_PRODUCTS_API_URL}/customers/${uniqueCode}/credit_cards`);
    const loans$ = this.httpService.get(`${process.env.CUSTOMER_PRODUCTS_API_URL}/customers/${uniqueCode}/loans`);

    // Handle Promises with async/await
    const [customerResponse, accountsResponse, cardsResponse, loansResponse] = await Promise.all([
      lastValueFrom(customerProfile$),
      lastValueFrom(accounts$),
      lastValueFrom(cards$),
      lastValueFrom(loans$),
    ]);

    const customerProfile = customerResponse.data;
    const accounts = accountsResponse.data;
    const cards = cardsResponse.data;
    const loans = loansResponse.data;

    return {
      ...customerProfile,
      products: [...accounts, ...cards, ...loans],
    };
  }

  encrypt(value: string) {
    const encrypted = this.cryptoUtils.encrypt(value);
    return {encrypted};
  }
  decrypt(value: string) {
    const decrypted = this.cryptoUtils.decrypt(value);
    return {decrypted}; 
  }
}
