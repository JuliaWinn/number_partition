function [final] = visualize(d,unit, bool)
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
    if unit == "ms"
        a = "Average Time(ms):";
        y = "Size of Residue";
        if bool == "true" 
            t = "Time Comparison of Random Algorithms (Prepartitioning) + KK";
        else
            t = "Time Comparison of Random Algorithms (Standard) + KK";
        end
    else
        a = "Average Residue:";
        y = "Size of Residue";
        if bool == "true"
            t = "Residue Comparison of Random Algorithms (Prepartitioning) + KK";
        else
            t = "Residue Comparison of Random Algorithms (Standard) + KK";
        end
    end
    
    s1 = strcat('Random: ', num2str(p1_avg) , " ", unit)
    s2 = strcat('Hill: ', num2str(p2_avg) , " ", unit)
    s3 = strcat('Anneal: ', num2str(p3_avg), " ", unit)
    s4 = strcat('KK: ', num2str(p4_avg), " ", unit)
    
    % now make it pretty
    plot(s,final);
    legend('Repeated Random','Hill Climbing','Simulated Annealing','KarmarkarKarp');
    xlabel('Trial Number');
    ylabel(y);
    % title('Time Comparison of Random Algorithms + KK (Prepartitioning)');
    % title('Residue Comparison of Random Algorithms + KK (Prepartitioning)');
    title(t);
    text(8,32000000, a);
    text(10,30000000, s1);
    text(10,28000000, s2);
    text(10,26000000, s3);
    text(10,24000000, s4);
    
    % text(16,120, a);
    % text(17,110, s1);
    % text(17,100, s2);
    % text(17,90, s3);
    % text(17,80, s4);
    
    % text(16,120, a);
    % text(17,110, s1);
    % text(17,100, s2);
    % text(17,90, s3);
    % text(17,80, s4);
    
end