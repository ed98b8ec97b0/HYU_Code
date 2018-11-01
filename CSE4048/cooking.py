# -*- coding: utf-8 -*-

'''
DNN to classify MNIST handwritten digits
'''

import os
os.environ['TF_CPP_MIN_LOG_LEVEL']='2'

import tensorflow as tf
import numpy as np
import json



# tf Graph input
X = tf.placeholder(tf.float32, [None, n_input])
Y = tf.placeholder(tf.float32, [None, n_classes])

train_data = json.load(open('train.json'))
test_data = json.load(open('test.json'))

# Parameters
learning_rate = 0.001
n_epochs = 60
batch_size = 100
display_step = 1

# Network Parameters
n_hidden_1 = 1000 # 1st layer number of features
n_hidden_2 = 1000 # 2nd layer number of features
n_input = 6714 # MNIST data input (img shape: 28*28)
n_classes = 20 # MNIST total classes (0-9 digits)

print('# Trian samples : {0}'.format(len(train_data)))
print('# Test samples : {0}'.format(len(test_data)))

train_example = train_data[:27841]
valid_example = train_data[27841:]

ingredients = []
for recipe in train_data:
    for ingredient in recipe['ingredients']:
        if ingredient not in ingredients:
            ingredients.append(ingredient)

print("# of ingredients:", len(ingredients))
ingredients.sort()

cuisines = []
for recipe in train_data:
    if recipe['cuisine'] not in cuisines:
        cuisines.append(recipe['cuisine'])

print("# of cuisines:", len(cuisines))
cuisines.sort()

def next_batch(data, batch_size):
    global step
    global ingredients
    global cuisines        
    
    X_out = np.zeros(shape=(batch_size, len(ingredients)), dtype=np.float)
    y_out = np.zeros(shape=(batch_size, len(cuisines)), dtype=np.float)
    
    j = 0
    for i in range(step * batch_size, (step + 1) * batch_size):
        
        if (i == len(data)):
            break;
        
        x = np.zeros(len(ingredients), dtype=np.float)
        y = np.zeros(len(cuisines), dtype=np.float)

        for ingredient in data[i]['ingredients']:
            x[ingredients.index(ingredient)] = 1

        y[cuisines.index(data[i]['cuisine'])] = 1

        X_out[j] = x
        y_out[j] = y
        j += 1

    step += 1

    return (X_out, y_out)

# Store layers weight & bias
weights = {
    'h1': tf.Variable(tf.random_normal([n_input, n_hidden_1])),
    'h2': tf.Variable(tf.random_normal([n_hidden_1, n_hidden_2])),
    'out': tf.Variable(tf.random_normal([n_hidden_2, n_classes]))
}

biases = {
    'b1': tf.Variable(tf.random_normal([n_hidden_1])),
    'b2': tf.Variable(tf.random_normal([n_hidden_2])),
    'out': tf.Variable(tf.random_normal([n_classes]))
}

# Construct model
pred = multilayer_perceptron(X, weights, biases)

# Define loss and optimizer
loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=pred, labels=Y))

#optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate).minimize(loss)
optimizer = tf.train.RMSPropOptimizer(learning_rate=learning_rate).minimize(loss)

import sys

# Launch the graph
with tf.Session() as sess:
    sess.run(tf.global_variables_initializer())

    # Training 
    for i in range(n_epochs):
        step = 0
        total_loss = 0.
        n_batches = int(len(train_example)/batch_size)
        # Loop over all batches
        for j in range(n_batches):
            batch = next_batch(train_example, batch_size)
            X_batch = batch[0]
            Y_batch = batch[1]
            sys.stdout.write('\r[{0}] start_idx: {1}, end_idx: {2}'.format(j, step * batch_size, (step+1) * batch_size))
            
            # Run optimization op (backprop) and cost op (to get loss value)
            _, l = sess.run([optimizer, loss], feed_dict={X: X_batch, Y: Y_batch})
            # Compute average loss
            total_loss += l
        # Display logs per epoch step
        print('\nAverage loss epoch {0}: {1}'.format(i, total_loss/n_batches))

    print("Optimization Finished!")


    correct_preds = tf.equal(tf.argmax(pred, axis=1), tf.argmax(Y, axis=1))
    accuracy = tf.reduce_sum(tf.cast(correct_preds, tf.float32))
    
    n_batches = int(len(valid_example)/batch_size)
    total_correct_preds = 0
    
    for i in range(n_batches):
        step = 0
        X_batch, Y_batch = next_batch(valid_example, batch_size)
        accuracy_batch = sess.run(accuracy, feed_dict={X: X_batch, Y: Y_batch}) 
        total_correct_preds += accuracy_batch   
    
    print('Accuracy {0}'.format(total_correct_preds/len(valid_example)))
    print('n_epochs: {0}'.format(n_epochs))
    print('batch_size: {0}'.format(batch_size))
    print('n_hidden: {0}'.format(n_hidden_1))

    
# epoch 20: Accuracy 0.5285343166010223
# epoch 30: Accuracy 0.5684236989860052