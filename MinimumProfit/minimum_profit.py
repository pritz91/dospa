#########################################################################################################################################################################################################################################################################
# You buy an imported packet of candies. In order to gain name and fame in the city, you decide to sell these candies with the minimum profit (profit > 0). There exists an added constraint, the selling price will be made up of the same digits as the buying price. #
# (ex.)                                                                                                                                                                                                                                                                 #
#     buying price : 4139                                                                                                                                                                                                                                               #
#     selling price : 4193.                                                                                                                                                                                                                                             #
#                                                                                                                                                                                                                                                                       #
# (ex.)                                                                                                                                                                                                                                                                 #
#     buying price : 8642                                                                                                                                                                                                                                               #
#     selling price : -1                                                                                                                                                                                                                                                #
#                                                                                                                                                                                                                                                                       #
# In this case, there exists no such selling price                                                                                                                                                                                                                      #
#########################################################################################################################################################################################################################################################################



# Splits the digits from the number and returns a list 
# ex: splitDigits(235)    : output [2,3,5]
def splitDigits(number):
    if(number == 0):
        return []
    return splitDigits(number/10) + [(number%10)]



# Returns the nextGreater element from the list to element(parameter)
def nextGreaterElement(element, list1):
    list1.sort()
    for elt in list1:
        if(elt > element):
            return elt

# Convert a list of integers to a number
def toInt(list1):
    list1 = map(str,list1)
    return int("".join(list1))


# will find the minimum profit for the problem statement
def findMinimumProfit(number):
    digits = splitDigits(number)
    numberOfDigits = len(digits)
    if(numberOfDigits < 1):
        return -1
    elif (numberOfDigits == 1):
        return toInt(digits + digits)
    else:
        i = numberOfDigits - 1
        j = i -1
        while(j>=0):
            if(digits[j] < digits[i]):
                nextBiggerValue = nextGreaterElement(digits[j], digits[j+1:])    
                changedDigits = digits[i:]
                changedDigits.remove(nextBiggerValue)
                changedDigits.append(digits[j])                
                changedDigits.sort()
                return toInt(digits[:j] + [nextBiggerValue] + changedDigits)
            j-=1
            i-=1

        digits.sort()
        digits = map(str,digits)
        return toInt([digits[0]] + digits)

print findMinimumProfit(4139)
print findMinimumProfit(8642)
print findMinimumProfit(1432)
