#ifndef SR_DEMO_ICE
#define SR_DEMO_ICE

#include <Ice/BuiltinSequences.ice>

module Slice
{
	enum CategoryName {K1, K2, K3, K4, K5};

    interface User
    {
        long getId();
        CategoryName getCategory();
        string getCreationTime();
    };
};

#endif
