def solution(enroll, referral, seller, amount):
    salary=dict()
    parent=dict()
    
    salary['-']=0
    for i in range(len(enroll)):
        salary[enroll[i]]=0
        parent[enroll[i]]=referral[i]
        
    for i in range(len(seller)):
        s=seller[i]
        p=amount[i]*100
        
        while s!='-':
            salary[s]+=p-(p//10)
            p=p//10
            if p==0:break
            s=parent[s]
    
    return [salary[e] for e in enroll]