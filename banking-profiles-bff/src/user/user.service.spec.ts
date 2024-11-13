import { Test, TestingModule } from '@nestjs/testing';
import { UserService } from './user.service';
import * as bcrypt from 'bcryptjs';
import { getModelToken } from '@nestjs/mongoose';
import { User } from './user.schema';
import { UserDocument } from './user.schema';

// Mock Mongoose Model
class MockUserModel {
  constructor() {}

  // create should be an instance method
  async create(data: any): Promise<any> {
    return Promise.resolve({ id: 'newUserId', ...data });
  }

  findOne = jest.fn().mockReturnThis();
  exec = jest.fn();
}

describe('UserService', () => {
  let service: UserService;
  let mockUserModelInstance: MockUserModel;

  beforeEach(async () => {
    mockUserModelInstance = new MockUserModel();

    const module: TestingModule = await Test.createTestingModule({
      providers: [
        UserService,
        {
          provide: getModelToken(User.name),
          useValue: mockUserModelInstance,
        },
      ],
    }).compile();

    service = module.get<UserService>(UserService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  // Separate describe block for findUserByUsername
  describe('findUserByUsername', () => {
    it('should find a user by username', async () => {
      const mockUser: User = {
        username: 'testuser',
        password: 'hashed_password',
        salt: 'someSaltValue',
      };

      mockUserModelInstance.findOne.mockReturnValue({
        exec: jest.fn().mockResolvedValue(mockUser),
      });

      const user = await service.findUserByUsername('testuser');

      expect(mockUserModelInstance.findOne).toHaveBeenCalledWith({
        username: 'testuser',
      });
      expect(user).toEqual(mockUser);
    });
  });

  // Separate describe block for createUser
  describe('createUser', () => {
    it('should create a new user with hashed password', async () => {
      const userDto = {
        username: 'newuser',
        password: 'password',
      };
      const mockSalt = '$2a$10$someRandomSaltValue';
      const mockHashedPassword = '$2a$10$someRandomHashValue';

      jest
        .spyOn(bcrypt, 'genSalt')
        .mockImplementation(async () => Promise.resolve(mockSalt));
      jest
        .spyOn(bcrypt, 'hash')
        .mockImplementation(async () => Promise.resolve(mockHashedPassword));

      const createdUser = await service.createUser(
        userDto.username,
        userDto.password,
        mockSalt,
      );

      expect(createdUser).toEqual({
        id: 'newUserId',
        username: userDto.username,
        password: userDto.password,
        salt: mockSalt,
      });
    });
  });
});

