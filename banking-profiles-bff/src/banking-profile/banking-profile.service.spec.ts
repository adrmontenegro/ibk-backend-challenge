import { Test, TestingModule } from '@nestjs/testing';
import { BankingProfileService } from './banking-profile.service';
import { HttpService } from '@nestjs/axios';
import { of } from 'rxjs';
import { CryptoUtils } from '../utils/cryptoUtils';
import { AxiosResponse } from 'axios';

describe('BankingProfileService', () => {
  let service: BankingProfileService;
  let httpService: HttpService;
  let cryptoUtils: CryptoUtils;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        BankingProfileService,
        {
          provide: HttpService,
          useValue: {
            get: jest.fn(() => of({ data: {}, status: 200, statusText: 'OK', headers: {}, config: {} } as AxiosResponse<unknown>)),
          },
        },
        CryptoUtils,
      ],
    }).compile();

    service = module.get<BankingProfileService>(BankingProfileService);
    httpService = module.get<HttpService>(HttpService);
    cryptoUtils = module.get<CryptoUtils>(CryptoUtils);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('getBankingProfile', () => {
    it('should return the banking profile for a given encrypted unique code', async () => {
      const encryptedUniqueCode = 'encrypted123';
      const uniqueCode = '123';
      const customerProfile = {
        firstName: 'John',
        lastName: 'Doe',
        uniqueCode,
      };
      const accounts = [
        {
          id: 'account1',
          balance: 100,
        },
      ];
      const cards = [
        {
          id: 'card1',
          limit: 1000,
        },
      ];
      const loans = [
        {
          id: 'loan1',
          amount: 5000,
        },
      ];

      jest.spyOn(cryptoUtils, 'decrypt').mockReturnValue(uniqueCode);
      jest.spyOn(httpService, 'get').mockReturnValueOnce(of({ data: customerProfile, status: 200, statusText: 'OK', headers: {}, config: {} } as AxiosResponse<unknown>));
      jest.spyOn(httpService, 'get').mockReturnValueOnce(of({ data: accounts, status: 200, statusText: 'OK', headers: {}, config: {} } as AxiosResponse<unknown>));
      jest.spyOn(httpService, 'get').mockReturnValueOnce(of({ data: cards, status: 200, statusText: 'OK', headers: {}, config: {} } as AxiosResponse<unknown>));
      jest.spyOn(httpService, 'get').mockReturnValueOnce(of({ data: loans, status: 200, statusText: 'OK', headers: {}, config: {} } as AxiosResponse<unknown>));

      const bankingProfile = await service.getBankingProfile(
        encryptedUniqueCode,
      );

      expect(cryptoUtils.decrypt).toHaveBeenCalledWith(encryptedUniqueCode);
      expect(httpService.get).toHaveBeenCalledWith(
        `/customers/${uniqueCode}`,
      );
      expect(httpService.get).toHaveBeenCalledWith(
        `http://127.0.0.1:8080/customers/${uniqueCode}/savings_accounts`,
      );
      expect(httpService.get).toHaveBeenCalledWith(
        `/customers/${uniqueCode}/credit_cards`,
      );
      expect(httpService.get).toHaveBeenCalledWith(
        `http://127.0.0.1:8080/customers/${uniqueCode}/loans`,
      );
      expect(bankingProfile).toEqual({
        ...customerProfile,
        products: [...accounts, ...cards, ...loans],
      });
    });
  });

  describe('encrypt', () => {
    it('should encrypt a value', async () => {
      const value = 'test';
      const encryptedValue = 'encryptedTest';

      jest.spyOn(cryptoUtils, 'encrypt').mockReturnValue(encryptedValue);

      const result = await service.encrypt(value);

      expect(cryptoUtils.encrypt).toHaveBeenCalledWith(value);
      expect(result).toEqual({ encrypted: encryptedValue });
    });
  });

  describe('decrypt', () => {
    it('should decrypt a value', async () => {
      const value = 'encryptedTest';
      const decryptedValue = 'test';

      jest.spyOn(cryptoUtils, 'decrypt').mockReturnValue(decryptedValue);

      const result = await service.decrypt(value);

      expect(cryptoUtils.decrypt).toHaveBeenCalledWith(value);
      expect(result).toEqual({ decrypted: decryptedValue });
    });
  });
});

