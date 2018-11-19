import torch
import torch.nn as nn
import torch.nn.functional as F
from torch.utils.data import Dataset, DataLoader
import numpy as np
import matplotlib.pyplot as plt

class CardDataLoader(Dataset):
    """Face Landmarks dataset."""

    def __init__(self, org_data_feat, org_data_label, batch_size):
        self.data_all_feat = org_data_feat
        self.data_all_label = org_data_label
        self.batch_size = batch_size

    def __len__(self):
        if(len(self.data_all_feat) % self.batch_size == 0):
            return len(self.data_all_feat) // self.batch_size
        else:
            return (len(self.data_all_feat) // self.batch_size)+1
        #return len(self.data_all_feat)

    def __getitem__(self, idx):
        begin = self.batch_size*idx
        end = self.batch_size*(idx+1)
        feats = self.data_all_feat[begin:end]
        labels = self.data_all_label[begin:end]
        return feats, labels

##############################################################################################################
#
#importing data files
#
training_file = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/trainingDataMovement.txt", "r")
training_data = training_file.readlines()
training_suit = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/trainingDataSuit.txt", "r")
training_data_suit = training_suit.readlines()
training_rank = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/trainingDataRank.txt", "r")
training_data_rank = training_rank.readlines()
training_uniqueness = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/trainingDataUniqueness.txt", "r")
training_data_uniqueness = training_uniqueness.readlines()
print("reading training data done")

validation_file = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/validationDataMovement.txt", "r")
validation_data = validation_file.readlines()
validation_suit = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/validationDataSuit.txt", "r")
validation_data_suit = validation_suit.readlines()
validation_rank = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/validationDataRank.txt", "r")
validation_data_rank = validation_rank.readlines()
validation_uniqueness = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/validationDataUniqueness.txt", "r")
validation_data_uniqueness = validation_uniqueness.readlines()
print("reading validation data done")

testing_file = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/testingDataMovement.txt", "r")
testing_data = testing_file.readlines()
testing_suit = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/testingDataSuit.txt", "r")
testing_data_suit = testing_suit.readlines()
testing_rank = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/testingDataRank.txt", "r")
testing_data_rank = testing_rank.readlines()
testing_uniqueness = open("/home/benjaminsang/PycharmProjects/BirdsOfAFeatherNN/venv/data/testingDataUniqueness.txt", "r")
testing_data_uniqueness = testing_uniqueness.readlines()
print("reading testing data done")

string_training_data = []
string_training_data_rank = []
string_training_data_suit = []
string_training_data_uniqueness = []
input_training_data = []
input_training_data_rank = []
input_training_data_suit = []
input_training_data_uniqueness = []
input_training_data_total = []
output_training_data = []

string_validation_data = []
string_validation_data_rank = []
string_validation_data_suit = []
string_validation_data_uniqueness = []
input_validation_data = []
input_validation_data_rank = []
input_validation_data_suit = []
input_validation_data_uniqueness = []
input_validation_data_total = []
output_validation_data = []

string_testing_data = []
string_testing_data_rank = []
string_testing_data_suit = []
string_testing_data_uniqueness = []
input_testing_data = []
input_testing_data_rank = []
input_testing_data_suit = []
input_testing_data_uniqueness = []
input_testing_data_total = []
output_testing_data = []

# our data
# N = 1862
# N_valid = 1120
# N_test = 744

N = 1862
N_valid = 1120
N_test = 997018

# their data
# N = 682728
# N_valid = 363022
# N_test = 347680

# combined data
# N = 684590
# N_valid = 364142
# N_test = 1344698

D_in = 32
D_out = 2
H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15 = 30, 28, 26, 24, 22, 20, 18, 16, 14, 12, 10, 8, 6, 4, 2

######################################################
#
#creating training set
#
for line in training_data:
    data = line.split()
    string_training_data.append(data)
for line in training_data_suit:
    data = line.split()
    string_training_data_suit.append(data)
for line in training_data_rank:
    data = line.split()
    string_training_data_rank.append(data)
for line in training_data_uniqueness:
    data = line.split()
    string_training_data_uniqueness.append(data)
print("training set to string complete")

#####################################################
#
#creating validation set
#
for line in validation_data:
    data = line.split()
    string_validation_data.append(data)
for line in validation_data_suit:
    data = line.split()
    string_validation_data_suit.append(data)
for line in validation_data_rank:
    data = line.split()
    string_validation_data_rank.append(data)
for line in validation_data_uniqueness:
    data = line.split()
    string_validation_data_uniqueness.append(data)
print("validation set to string complete")

####################################################
#
#creating testing set
#
for line in testing_data:
    data = line.split()
    string_testing_data.append(data)
for line in testing_data_suit:
    data = line.split()
    string_testing_data_suit.append(data)
for line in testing_data_rank:
    data = line.split()
    string_testing_data_rank.append(data)
for line in testing_data_uniqueness:
    data = line.split()
    string_testing_data_uniqueness.append(data)
print("testing set to string complete")

#####################################################
#
#converting training set into data training set
#
for data_lines in string_training_data:
    if (data_lines[0] == '+1'):
        output = []
        output.append(1.0)
        output.append(0.0)
        output_training_data.append(output)
    if (data_lines[0] == '-1'):
        output = []
        output.append(0.0)
        output.append(1.0)
        output_training_data.append(output)

for data_lines in string_training_data:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range (1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_training_data.append(line_data)
    position = 0
for data_lines in string_training_data_rank:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range (1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_training_data_rank.append(line_data)
    position = 0
for data_lines in string_training_data_suit:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range (1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_training_data_suit.append(line_data)
    position = 0
for data_lines in string_training_data_uniqueness:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range (1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_training_data_uniqueness.append(line_data)
    position = 0
print("training data to arrays complete")
##########################################################
#
#converting validation set into data validation set
#
for data_lines in string_validation_data:
    if (data_lines[0] == '+1'):
        output = []
        output.append(1.0)
        output.append(0.0)
        output_validation_data.append(output)
    if (data_lines[0] == '-1'):
        output = []
        output.append(0.0)
        output.append(1.0)
        output_validation_data.append(output)
for data_lines in string_validation_data:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range(1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_validation_data.append(line_data)
    position = 0
for data_lines in string_validation_data_rank:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range (1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_validation_data_rank.append(line_data)
    position = 0
for data_lines in string_validation_data_suit:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range (1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_validation_data_suit.append(line_data)
    position = 0
for data_lines in string_validation_data_uniqueness:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range (1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_validation_data_uniqueness.append(line_data)
    position = 0
print("validation data to arrays complete")
#########################################################
#
#converting testing set into data testing sets
#
for data_lines in string_testing_data:
    if (data_lines[0] == '+1'):
        output = []
        output.append(1.0)
        output.append(0.0)
        output_testing_data.append(output)
    if (data_lines[0] == '-1'):
        output = []
        output.append(0.0)
        output.append(1.0)
        output_testing_data.append(output)
for data_lines in string_testing_data:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range(1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_testing_data.append(line_data)
    position = 0
for data_lines in string_testing_data_rank:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range(1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_testing_data_rank.append(line_data)
    position = 0
for data_lines in string_testing_data_suit:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range(1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_testing_data_suit.append(line_data)
    position = 0
for data_lines in string_testing_data_uniqueness:
    line_data = []
    for i in range (4):
        line_data.append([])
    position = 0
    count = 0
    for i in range (1, len(data_lines)):
        line_data[position].append(float(data_lines[i]))
        count += 1
        if (count == 4):
            position += 1
            count = 0
    input_testing_data_uniqueness.append(line_data)
    position = 0
print("testing data to arrays complete")
##############################################################
#
#combining suit, data, and rank into one big tensor
#
for i in range(0, N):
    training_three_channels = []
    # training_three_channels.append(input_training_data[i])
    # training_three_channels.append(input_training_data_rank[i])
    # training_three_channels.append(input_training_data_suit[i])
    training_three_channels.append(input_training_data_uniqueness[i])
    input_training_data_total.append(training_three_channels)
for i in range(0, N_valid):
    validation_three_channels = []
    # validation_three_channels.append(input_validation_data[i])
    # validation_three_channels.append(input_validation_data_rank[i])
    # validation_three_channels.append(input_validation_data_suit[i])
    validation_three_channels.append(input_validation_data_uniqueness[i])
    input_validation_data_total.append(validation_three_channels)
for i in range(0, N_test):
    testing_three_channels = []
    # testing_three_channels.append(input_testing_data[i])
    # testing_three_channels.append(input_testing_data_rank[i])
    # testing_three_channels.append(input_testing_data_suit[i])
    testing_three_channels.append(input_testing_data_uniqueness[i])
    input_testing_data_total.append(testing_three_channels)
print("combining data complete")
######################################################################
#
#converting into numpy arrays
#

numpy_training_output_data = np.asarray(output_training_data, dtype=np.float32)
numpy_training_input_data = np.asarray(input_training_data_total, dtype=np.float32)

numpy_validation_output_data = np.asarray(output_validation_data, dtype=np.float32)
numpy_validation_input_data = np.asarray(input_validation_data_total, dtype=np.float32)

numpy_testing_output_data = np.asarray(output_testing_data, dtype=np.float32)
numpy_testing_input_data = np.asarray(input_testing_data_total, dtype=np.float32)

print("conversion to numpy arrays complete")
##########################################################################3
#
#converting to tensors
#
tensor_training_output_data = torch.from_numpy(numpy_training_output_data).float()
tensor_training_input_data = torch.from_numpy(numpy_training_input_data).float()

tensor_validation_output_data = torch.from_numpy(numpy_validation_output_data).float()
tensor_validation_input_data = torch.from_numpy(numpy_validation_input_data).float()

tensor_testing_output_data = torch.from_numpy(numpy_testing_output_data).float()
tensor_testing_input_data = torch.from_numpy(numpy_testing_input_data).float()

print("conversion to tensors complete")

########################################################
# debugging images

# def imshow_three(img):
#     npimg = img.numpy()
#     plt.imshow(npimg[0, :, :])
#     plt.imshow(npimg[1, :, :])
#     plt.imshow(npimg[2, :, :])
#     plt.imshow(npimg[3, :, :])
#     plt.imshow(npimg)
#
#
# def imshow_one(img):
#     img = img / (3 - 0)
#     npimg = img.numpy()
#     plt.imshow(npimg)
#
# img_test = tensor_testing_input_data[3]
# imshow_three(img_test)

##################################################################
#
#image recognizer nn model
#
class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()
        # in_ch, out_ch, kernel_size, stride, padding
        self.conv1 = nn.Conv2d(1, 2, 3, 1, 1) # 6 was chosen because tutorial is 3-6
        self.conv1_batch = nn.BatchNorm2d(4)
        self.pool = nn.MaxPool2d(2, 2)
        self.fc1 = nn.Linear(2 * 2 * 2, 32)
        self.fc2 = nn.Linear(32, 30)
        self.fc3 = nn.Linear(30, 28)
        self.fc4 = nn.Linear(28, 26)
        self.fc5 = nn.Linear(26, 24)
        self.fc6 = nn.Linear(24, 22)
        self.fc7 = nn.Linear(22, 20)
        self.fc8 = nn.Linear(20, 18)
        self.fc9 = nn.Linear(18, 16)
        self.fc10 = nn.Linear(16, 14)
        self.fc11 = nn.Linear(14, 12)
        self.fc12 = nn.Linear(12, 10)
        self.fc13 = nn.Linear(10, 8)
        self.fc14 = nn.Linear(8, 6)
        self.fc15 = nn.Linear(6, 4)
        self.fc16 = nn.Linear(4, 2)

    def forward(self, x):
        x = self.pool(F.relu(self.conv1(x)))
        x = x.view(-1, 2 * 2 * 2) # based on size calculation
        x = F.relu(self.fc1(x))
        x = F.relu(self.fc2(x))
        x = F.relu(self.fc3(x))
        x = F.relu(self.fc4(x))
        x = F.relu(self.fc5(x))
        x = F.relu(self.fc6(x))
        x = F.relu(self.fc7(x))
        x = F.relu(self.fc8(x))
        x = F.relu(self.fc9(x))
        x = F.relu(self.fc10(x))
        x = F.relu(self.fc11(x))
        x = F.relu(self.fc12(x))
        x = F.relu(self.fc13(x))
        x = F.relu(self.fc14(x))
        x = F.relu(self.fc15(x))
        x = self.fc16(x)
        return x

model = Net()

################################################################
#
#using gpu
#
device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")
model.to(device)

######################################################################

d = CardDataLoader(tensor_training_input_data, tensor_training_output_data, 100)

################################################################
#
#send tensors to gpu
#

#defining loss function
loss_fn = torch.nn.MSELoss(reduction='sum')

#defining optimizer and scheduler for learning rate
learning_rate = 0.001
optimizer = torch.optim.Adam(model.parameters(), lr=learning_rate)
scheduler = torch.optim.lr_scheduler.StepLR(optimizer, step_size=130, gamma=0.1)

########################################################################
#
#training the neural network
#
for t in range(500):
    scheduler.step()
    running_loss=0.0
    for ii in range(len(d)):
        feats, labels = d[ii]

        feats = feats.to(device)
        labels = labels.to(device)   

        output_pred = model(feats)

        # pos_train_samples = (output_pred[:, 0] > output_pred[:, 1]).sum()
        # neg_train_samples = (output_pred[:, 0] <= output_pred[:, 1]).sum()
        # output_pred_pos = torch.cuda.FloatTensor([1, 0]).expand(pos_train_samples, -1)
        # output_pred[output_pred[:, 0] > output_pred[:, 1]] = output_pred_pos
        # output_pred_neg = torch.cuda.FloatTensor([0, 1]).expand(neg_train_samples, -1)
        # output_pred[output_pred[:, 0] <= output_pred[:, 1]] = output_pred_neg

        loss = loss_fn(output_pred, labels)

        optimizer.zero_grad()

        loss.backward()

        optimizer.step()

        running_loss += loss.item()
        #print(loss.item())
        if ii == (len(d)-1):  # print every 2000 mini-batches
            print('[%d, %5d] loss: %.3f' %
                  (t + 1, ii + 1, running_loss / len(d)))
            running_loss = 0.0

########################################################################
#
#checking accuracy with training data after training
#
# output_train_pred = model(tensor_training_input_data)
#
# loss = loss_fn(output_train_pred, tensor_training_output_data)
#
# # pos_samples = (output_train_pred[:, 0] > output_train_pred[:, 1]).sum()
# # neg_samples = (output_train_pred[:, 0] <= output_train_pred[:, 1]).sum()
# # output_train_pred_pos = torch.cuda.FloatTensor([1, 0]).expand(pos_samples, -1)
# # output_train_pred[output_train_pred[:, 0] > output_train_pred[:, 1]] = output_train_pred_pos
# # output_train_pred_neg = torch.cuda.FloatTensor([0, 1]).expand(neg_samples, -1)
# # output_train_pred[output_train_pred[:, 0] <= output_train_pred[:, 1]] = output_train_pred_neg
#
# y_all = torch.cat((tensor_training_output_data, output_train_pred), 1)
# print(y_all)
# # In order to compute the accuracy, you need to find the samples with the same label
# error_train = tensor_training_output_data.sub(output_train_pred)
# error_train = error_train.abs_()
# error_train = error_train.sum(1)  # summation column-wise, i.e. all output nodes
# # find all zero values (i.e. each zero will count as one),
# mask_error_train = (error_train == 0.0)
# # add them up, (we need to convert the result into float type to avoid getting zero)
# number_of_correct = mask_error_train.sum().type(torch.FloatTensor)
# # then divide by the total number of samples.
# ACC_train = number_of_correct.div(N * 1.0) * 100.0
# print("Accuracy = %f %%" % ACC_train)

tensor_validation_output_data = tensor_validation_output_data.to(device)
tensor_validation_input_data = tensor_validation_input_data.to(device)
tensor_testing_output_data = tensor_testing_output_data.to(device)
tensor_testing_input_data = tensor_testing_input_data.to(device)

######################################################################
#
#checking accuracy with validation data after training
#
output_valid_pred = model(tensor_validation_input_data)

loss = loss_fn(output_valid_pred, tensor_validation_output_data)

pos_samples = (output_valid_pred[:, 0] > output_valid_pred[:, 1]).sum()
neg_samples = (output_valid_pred[:, 0] <= output_valid_pred[:, 1]).sum()
output_valid_pred_pos = torch.cuda.FloatTensor([1, 0]).expand(pos_samples, -1)
output_valid_pred[output_valid_pred[:, 0] > output_valid_pred[:, 1]] = output_valid_pred_pos
output_valid_pred_neg = torch.cuda.FloatTensor([0, 1]).expand(neg_samples, -1)
output_valid_pred[output_valid_pred[:, 0] <= output_valid_pred[:, 1]] = output_valid_pred_neg

y_all = torch.cat((tensor_validation_output_data, output_valid_pred), 1)
print(y_all)
# In order to compute the accuracy, you need to find the samples with the same label
error_valid = tensor_validation_output_data.sub(output_valid_pred)
error_valid = error_valid.abs_()
error_valid = error_valid.sum(1)  # summation column-wise, i.e. all output nodes
# find all zero values (i.e. each zero will count as one),
mask_error_valid = (error_valid == 0.0)
# add them up, (we need to convert the result into float type to avoid getting zero)
number_of_correct = mask_error_valid.sum().type(torch.FloatTensor)
# then divide by the total number of samples.
ACC_valid = number_of_correct.div(N_valid * 1.0) * 100.0
print("Accuracy = %f %%" % ACC_valid)
#
######################################################################################
#
#checking accuracy with testing data after training
#
output_test_pred = model(tensor_testing_input_data)

loss = loss_fn(output_test_pred, tensor_testing_output_data)

pos_samples = (output_test_pred[:, 0] > output_test_pred[:, 1]).sum()
neg_samples = (output_test_pred[:, 0] <= output_test_pred[:, 1]).sum()
output_test_pred_pos = torch.cuda.FloatTensor([1, 0]).expand(pos_samples, -1)
output_test_pred[output_test_pred[:, 0] > output_test_pred[:, 1]] = output_test_pred_pos
output_test_pred_neg = torch.cuda.FloatTensor([0, 1]).expand(neg_samples, -1)
output_test_pred[output_test_pred[:, 0] <= output_test_pred[:, 1]] = output_test_pred_neg

y_all = torch.cat((tensor_testing_output_data, output_test_pred), 1)
print(y_all)
# In order to compute the accuracy, you need to find the samples with the same label
error_test = tensor_testing_output_data.sub(output_test_pred)
error_test = error_test.abs_()
error_test = error_test.sum(1)  # summation column-wise, i.e. all output nodes
# find all zero values (i.e. each zero will count as one),
mask_error_test = (error_test == 0.0)
# add them up, (we need to convert the result into float type to avoid getting zero)
number_of_correct = mask_error_test.sum().type(torch.FloatTensor)
# then divide by the total number of samples.
ACC_test = number_of_correct.div(N_test * 1.0) * 100.0
print("Accuracy = %f %%" % ACC_test)

import sklearn.metrics as metrics

tensor_testing_output_data = tensor_testing_output_data.cpu().detach().numpy()
output_test_pred = output_test_pred.cpu().detach().numpy()

y_all_gt = tensor_testing_output_data[:, 0]
y_all_pred = output_test_pred[:, 0]

tn, fp, fn, tp = metrics.confusion_matrix(y_all_gt, y_all_pred).ravel()
print(tp, fp, fn, tn)
print(tp / (tp+fn))
print(tn / (tn+fp))
# f1_test = metrics.f1_score(y_all_gt, y_all_pred)
#print(f1_test)
#    for data in range(1862):
#        input = tensor_testing_input_data[data]
#        outputs = model(input)
#        _, predicted = torch.max(outputs.data, 1)
#        total += 1
#        correct += (predicted == tensor_testing_output_data[data]).item()

#print('Accuracy of the network on the 10000 test images: %d %%' % (100 * correct / total))