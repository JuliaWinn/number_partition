function [p1] = visualize(d)
    s = size(d,1);
    p1 = [];
    p2 = [];
    p3 = [];
    p4 = [];
    for i = 1:s
        x = mod(i,4);
        switch (x)
            case {1}
                p1 = [p1;d(i)];
            case {2}
                p2 = [p2;d(i)];
            case {3}
                p3 = [p3;d(i)];
            case {0}
                p4 = [p4;d(i)];
            otherwise 
                disp('???');
       endswitch 
    end
    s = [1:50];
    final = [p1,p2,p3,p4];
    
    p1_avg = sum(p1)/size(p1,1)
    p2_avg = sum(p2)/size(p2,1)
    p3_avg = sum(p3)/size(p3,1)
    p4_avg = sum(p4)/size(p4,1)
    
    % now make it pretty
    plot(s,final);
    legend('Repeated Random','Hill Climbing','Simulated Annealing','KarmarkarKarp');
    xlabel('Trial Number');
    ylabel('Time in Milliseconds');
    title('Time Comparison of Random Algorithms + KK');
    text(20.0,30.0,'Average Time:');
    text(21.0,27.0,'Random: 40.380 ms');
    text(21.0,24.0,'Hill: 3.0400 ms');
    text(21.0,21.0,'Anneal: 9 ms');
    text(21.0,18.0,'KK: 0.24000 ms');
    % final = final ./ 10;
    save randlist.mat final
    
end