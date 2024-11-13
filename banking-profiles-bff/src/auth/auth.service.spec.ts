import { JwtService } from '@nestjs/jwt';
import { Test, TestingModule } from '@nestjs/testing';
import { AuthService } from './auth.service';
import { UserService } from '../user/user.service';
import * as bcrypt from 'bcryptjs';

// Define a User interface for type safety
interface User {
  id: string;
  username: string;
  password: string;
  salt?: string; // Salt might be optional depending on your implementation
}

describe('AuthService', () => {
  let service: AuthService;
  let userService: UserService;
  let jwtService: JwtService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        AuthService,
        {
          provide: UserService,
          useValue: {
            findUserByUsername: jest.fn(),
            createUser: jest.fn(),
          },
        },
        {
          provide: JwtService,
          useValue: {
            sign: jest.fn().mockReturnValue('test-token'),
          },
        },
      ],
    }).compile();

    service = module.get<AuthService>(AuthService);
    userService = module.get<UserService>(UserService);
    jwtService = module.get<JwtService>(JwtService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  describe('validateUser', () => {
    it('should return user (without password) if username and password match', async () => {
      const user: User = {
        id: '1',
        username: 'testuser',
        password: 'hashed_password',
      };

      (userService.findUserByUsername as jest.Mock).mockResolvedValue(user);
      jest.spyOn(bcrypt, 'compare').mockImplementation(() => Promise.resolve(true));

      const result = await service.validateUser('testuser', 'hashed_password');

      expect(userService.findUserByUsername).toHaveBeenCalledWith('testuser');
      expect(bcrypt.compare).toHaveBeenCalledWith('hashed_password', user.password);
      expect(result).toEqual({ id: '1', username: 'testuser' });
    });

    it('should return null if username is not found', async () => {
      (userService.findUserByUsername as jest.Mock).mockResolvedValue(null);

      const result = await service.validateUser('nonexistentuser', 'password');

      expect(userService.findUserByUsername).toHaveBeenCalledWith('nonexistentuser');
      expect(result).toBeNull();
    });

    it('should return null if password does not match', async () => {
      const user: User = {
        id: '1',
        username: 'testuser',
        password: 'hashed_password',
      };

      (userService.findUserByUsername as jest.Mock).mockResolvedValue(user);
      jest.spyOn(bcrypt, 'compare').mockImplementation(() => Promise.resolve(false))

      const result = await service.validateUser('testuser', 'wrongpassword');

      expect(userService.findUserByUsername).toHaveBeenCalledWith('testuser');
      expect(bcrypt.compare).toHaveBeenCalledWith('wrongpassword', user.password);
      expect(result).toBeNull();
    });
  });

  describe('loginUser', () => {
    it('should return an access token', async () => {
      const user: User = {
        id: '1',
        username: 'testuser',
        password: 'hashed_password', // Not used in this test, but good to have for completeness
      };
      const result = await service.loginUser(user);

      expect(jwtService.sign).toHaveBeenCalledWith({
        username: user.username,
        sub: user.id,
      });
      expect(result).toEqual({ access_token: 'test-token' });
    });
  });

  describe('registerUser', () => {
    it('should create a new user', async () => {
      const userDto = {
        username: 'newuser',
        password: 'password',
      };
      const mockSalt = '$2a$10$someRandomSaltValue';
      const mockHashedPassword = '$2a$10$someRandomHashValue';

      // Mock genSalt and hash
      jest.spyOn(bcrypt, 'genSalt').mockImplementation(async () => Promise.resolve(mockSalt));
      jest.spyOn(bcrypt, 'hash').mockImplementation(async () => Promise.resolve(mockHashedPassword));

      // Mock createUser with a User object
      (userService.createUser as jest.Mock).mockResolvedValue({
        id: 'someUserId',
        ...userDto,
        password: mockHashedPassword, // Use mocked hashed password
        salt: mockSalt, // Use mocked salt
      });

      const result = await service.registerUser(userDto);

      expect(bcrypt.genSalt).toHaveBeenCalledWith(10); // Check if genSalt was called with the correct argument
      expect(bcrypt.hash).toHaveBeenCalledWith(userDto.password, mockSalt); // Check if hash was called with the correct arguments
      expect(userService.createUser).toHaveBeenCalledWith(
        userDto.username,
        mockHashedPassword, // Use mocked hashed password
        mockSalt, // Use mocked salt
      );
      expect(result).toEqual({
        id: 'someUserId',
        ...userDto,
        password: mockHashedPassword, // Use mocked hashed password
        salt: mockSalt, // Use mocked salt
      });
    });
  });

});
