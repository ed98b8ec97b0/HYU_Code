'''
DNN to classify MNIST handwritten digits
'''

import os
os.environ['TF_CPP_MIN_LOG_LEVEL']='2'

import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data

tf.logging.set_verbosity(tf.logging.ERROR)
mnist = input_data.read_data_sets("./data/mnist", one_hot=True)

import timeit
import random

# Parameters
learning_rate_list = []
n_epochs_list = []
batch_size_list = []

for _ in range(0, 100):
    learning_rate_list.append(random.uniform(0.005, 0.05))
    n_epochs_list.append(random.randrange(80, 240))
    batch_size_list.append(random.randrange(100, 200))

display_step = 1
times = 5


for learning_rate, n_epochs, batch_size in zip(learning_rate_list, n_epochs_list, batch_size_list):
    # Network parameters
    n_layers = 2
    n_units = 1600
    h_layers = {}

    print('-------------------------------------------------\n')
    print('learning rate: {0}\nepoch: {1}\nbatch size: {2}\nnumber of layers: {3}\nnumber of units per layer: {4}\n'.format(learning_rate, n_epochs, batch_size, n_layers, n_units))

    def set_params(h_layers):
        params = {0: 784}
        for i in range(0, len(h_layers)):
            params[i+1] = h_layers[i]
        params[len(params)] = 10
        return params

    for i in range(0, n_layers):
        h_layers[i] = n_units
    params = set_params(h_layers)

    # tf Graph input
    X = tf.placeholder(tf.float32, [batch_size, params[0]])
    Y = tf.placeholder(tf.float32, [batch_size, params[len(params)-1]])

    def multilayer_perceptron(X, weights, biases):
        for i in range(0, len(params)-2):
            X = tf.add(tf.matmul(X, weights[i]), biases[i])
            X = tf.nn.relu(X)
        out_layer = tf.matmul(X, weights[i+1]) + biases[i+1]
        return out_layer


    # Store layers weight & bias
    def init_weights(n):
        dicts = {}
        for i in range(0, n):
            dicts[i] = tf.Variable(tf.random_normal([params[i], params[i+1]]))
        return dicts

    def init_biases(n):
        dicts = {}
        for i in range(0, n):
            dicts[i] = tf.Variable(tf.random_normal([params[i+1]]))
        return dicts

    accuracys = {}
    sess_start = timeit.default_timer()
    for count in range(0, times):
        # Construct model
        model_start = timeit.default_timer()
        weights = init_weights(len(params)-1)
        biases = init_biases(len(params)-1)
        pred = multilayer_perceptron(X, weights, biases)

        # Define loss and optimizer
        loss = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits=pred, labels=Y))

        #optimizer = tf.train.AdamOptimizer(learning_rate=learning_rate).minimize(loss)
        optimizer = tf.train.RMSPropOptimizer(learning_rate=learning_rate).minimize(loss)


        # Launch the graph
        with tf.Session() as sess:
            sess.run(tf.global_variables_initializer())

            # Training 
            for i in range(n_epochs):
                total_loss = 0.
                n_batches = int(mnist.train.num_examples/batch_size)
                # Loop over all batches
                for j in range(n_batches):
                    X_batch, Y_batch = mnist.train.next_batch(batch_size)
                    # Run optimization op (backprop) and cost op (to get loss value)
                    _, l = sess.run([optimizer, loss], feed_dict={X: X_batch, Y: Y_batch})
                    # Compute average loss
                    total_loss += l
                # Display logs per epoch step
                # print('Average loss epoch {0}: {1}'.format(i, total_loss/n_batches))

            # print("Optimization Finished!\n\n")


            correct_preds = tf.equal(tf.argmax(pred, axis=1), tf.argmax(Y, axis=1))
            accuracy = tf.reduce_sum(tf.cast(correct_preds, tf.float32))
            
            n_batches = int(mnist.test.num_examples/batch_size)
            total_correct_preds = 0
            
            for i in range(n_batches):
                X_batch, Y_batch = mnist.test.next_batch(batch_size)
                accuracy_batch = sess.run(accuracy, feed_dict={X: X_batch, Y:Y_batch}) 
                total_correct_preds += accuracy_batch   
            
            model_end = timeit.default_timer()
            print("Model #{0}'s Accuracy {1} took {2:.0f}s".format(count+1, total_correct_preds/mnist.test.num_examples, model_end-model_start))
            accuracys[count] = total_correct_preds/mnist.test.num_examples
        
    
    print('\n==> Lowest Accuarcy: {0:.4f}'.format(min(accuracys.values())))
    sess_end = timeit.default_timer()

    print('==> Total learning time: {0:.0f}m {1:.0f}s\n'.format((sess_end-sess_start)/60, (sess_end-sess_start)%60))

print('-------------------------------------------------')
