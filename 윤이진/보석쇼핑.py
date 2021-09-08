def solution(gems):
    dic=dict()
    s=set(gems)
    for i in s:
        dic[i]=0
    kind=len(s)
    print(kind)
    
    gems.insert(0,'')
    l,r,cnt=1,1,1
    dic[gems[l]]=1
    answer=[1,len(gems)-1]
    
    while l<len(gems) and r<len(gems):
        if cnt<kind:
            r+=1
            if r>=len(gems): break
            
            if dic[gems[r]]==0:
                cnt+=1
            
            dic[gems[r]]+=1
        else:
            if r-l<answer[1]-answer[0]:
                answer=[l,r]
                
            if dic[gems[l]]==1:
                cnt-=1
                
            dic[gems[l]]-=1
            l+=1
            
    
    return answer